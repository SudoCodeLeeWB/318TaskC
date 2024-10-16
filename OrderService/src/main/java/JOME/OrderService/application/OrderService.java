package JOME.OrderService.application;

import JOME.OrderService.antiCorruption.*;
import JOME.OrderService.domain.entity.*;
import JOME.OrderService.domain.event.*;
import JOME.OrderService.domain.factory.*;
import JOME.OrderService.dto.*;
import JOME.OrderService.infrastructure.persistance.*;
import JOME.shared_events.*;
import JOME.OrderService.infrastructure.external.messaging.KafkaProducerService;
import JOME.OrderService.infrastructure.external.rest.RestPaymentClient;
import JOME.OrderService.domain.service.ShoppingCartDomainService;
import JOME.OrderService.domain.service.OrderDomainService;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.Optional;



@Service
public class OrderService {

    // Repositories
    private final CustomerRepostory customerRepostory;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartDomainService shoppingCartDomainService;
    private final OrderDomainService orderDomainService;
    private final ShoppingCartFactory shoppingCartFactory;
    private final OrderFactory orderFactory;


    // External Messaging
    private final KafkaProducerService kafkaProducerService;

    // External Rest
    private final RestPaymentClient restPaymentClient;


    public OrderService(CustomerRepostory customerRepostory, OrderRepository orderRepository, ProductRepository productRepository, ShoppingCartRepository shoppingCartRepository, KafkaProducerService kafkaProducerService, RestPaymentClient restPaymentClient, ShoppingCartDomainService shoppingCartDomainService, OrderDomainService orderDomainService, ShoppingCartFactory shoppingCartFactory, OrderFactory orderFactory) {
        this.customerRepostory = customerRepostory;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.kafkaProducerService = kafkaProducerService;
        this.restPaymentClient = restPaymentClient;
        this.shoppingCartDomainService = shoppingCartDomainService;
        this.orderDomainService = orderDomainService;
        this.shoppingCartFactory = shoppingCartFactory;
        this.orderFactory = orderFactory;
    }



    // Implementing use-cases

    // 1. Add a new product to the shopping cart
    @Transactional
    public ShoppingCartDTO addNewOrderLineItemToShoppingCart(Long customerId , Long productId , int quantity) {

        // setup shopping cart
        Optional<ShoppingCart> recentShoppingCart = shoppingCartRepository.findByCustomerId(customerId);
        ShoppingCart currentShoppingCart = recentShoppingCart.orElseGet(() -> shoppingCartFactory.createNewShoppingCart(customerId));

        // get product data from productRepository, using productId
        Optional<Product> newProductOpt = productRepository.findById(productId);
        Product newProduct = newProductOpt.orElseThrow(()-> new RuntimeException()); //  

        // add product to the shopping cart ( delegate )
        ShoppingCart savedResult = shoppingCartDomainService.addNewProductToShoppingCart(currentShoppingCart, newProduct, quantity );
        savedResult = shoppingCartRepository.save(savedResult); //

        return new ShoppingCartDTO(savedResult);
    }


    // 2. Change the Quantity of a OrderLineProduct ( shopping cart ) - Delete

    @Transactional
    public ShoppingCartDTO deleteOrderLineItemQuantity( Long customerId, Long productId , int quantity ){

        //setup shopping cart
        Optional<ShoppingCart> recentShoppingCart = shoppingCartRepository.findByCustomerId(customerId);
        ShoppingCart currentShoppingCart = recentShoppingCart.orElseThrow(() -> new RuntimeException()); //  

        // delete product quantity from shopping cart ( delegate )
        ShoppingCart savedResult = shoppingCartDomainService.removeProductQuantityFromShoppingCart(currentShoppingCart,productId, quantity );
        savedResult = shoppingCartRepository.save(savedResult); //  
        return new ShoppingCartDTO(savedResult);

    }


    // 3. Change the Quantity of a OrderLineProduct ( shopping cart ) - Add

    @Transactional
    public ShoppingCartDTO addOrderLineItemQuantity( Long customerId , Long productId , int quantity ){

        //setup shopping cart
        Optional<ShoppingCart> recentShoppingCart = shoppingCartRepository.findByCustomerId(customerId);
        ShoppingCart currentShoppingCart = recentShoppingCart.orElseThrow(() -> new RuntimeException()); //  

        // Add product quantity from shopping cart ( delegate )
        ShoppingCart savedResult = shoppingCartDomainService.addProductQuantityFromShoppingCart(currentShoppingCart,productId, quantity );
        savedResult = shoppingCartRepository.save(savedResult);

        return new ShoppingCartDTO(savedResult);

    }


    // 4. Place Order
    @Transactional
    public OrderDTO placeOrder( Long customerId ){

        // find Shopping Cart
        Optional<ShoppingCart> recentShoppingCart = shoppingCartRepository.findByCustomerId(customerId);
        ShoppingCart currentShoppingCart = recentShoppingCart.orElseThrow(() -> new RuntimeException()); //  

        // find customer information
        Optional<Customer> customer = customerRepostory.findById(customerId);
        Customer currentCustomer = customer.orElseThrow(() -> new RuntimeException()); //  

        // convert shopping cart into Order
        Order newOrder = orderFactory.createOrder(currentShoppingCart , currentCustomer);

        // process payment of this order
        boolean paymentResult = restPaymentClient.processPayment(currentCustomer);


        // Change the state of the Order - call orderDomain Service here
        // ( the state will be different based on the payment result )
        Order savedResult = orderDomainService.placeOrder(newOrder , paymentResult);

        // save the Order State
        Order savedState = orderRepository.save(savedResult);

        // because of id of Order -> After when we have a Order ID.
        if(paymentResult){
            // end of ShoppingCartLifeCycle
            currentShoppingCart.getOrderLineItemList().clear();
            shoppingCartRepository.delete(currentShoppingCart);

            // Raise New Event : OrderPlaced Event
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(savedState);
            kafkaProducerService.sendOrderPlacedEvent(orderPlacedEvent);
        }


        return new OrderDTO(savedState);

    }


    // 5. Cancel Order
    @Transactional
    public OrderDTO cancelOrder(Long orderId){

        // find order from Repository
        Optional<Order> order = orderRepository.findById(orderId);
        Order currentOrder = order.orElseThrow(RuntimeException::new);


        // Raise New Event : OrderCanceled Event
        OrderCanceledEvent orderCanceledEvent = new OrderCanceledEvent(currentOrder);
        kafkaProducerService.sendOrderCancelledEvent(orderCanceledEvent);


        // change state of this order
        Order savedResult = orderDomainService.cancelOrder(currentOrder);
        Order savedState = orderRepository.save(savedResult);
        return new OrderDTO(savedState);

   }




    // Usage from others ( customer repository management )
    // Kafka subscribe

    // for CustomerRRepository - CRUD
    public void handleCustomerCreated(){}
    public void handleCustomerDeleted(){}
    public void handleCustomerUpdated(){}


    // for ProductRepository - CRUD
    public void handleProductCreated(AddNewProductEventShared event){
        // apply ACL
        Product newProduct = ProductCreateEventMapper.mapFromEventToDomain(event);
        // Save it
        Product savedState = productRepository.save(newProduct);
    }



    public void handleProductDeleted(ProductDeleteEventShared event){

        // apply ACL
        Long targetId = DeleteProductEventMapper.mapFromEventToDomain(event);
        productRepository.deleteById(targetId);

    }



    public void handleProductUpdated(UpdateProductStockEventShared event){

        Long targetProductId = event.getId();
        Optional<Product> targetProduct = productRepository.findById(targetProductId);
        Product currentProduct = targetProduct.orElseThrow(() -> new RuntimeException());

        // apply ACL
        Product modifiedProduct = UpdateProductStockEventMapper.mapFromEventToDomain(currentProduct , event);

        // update product state
        Product savedState = productRepository.save(modifiedProduct);

    }


}






