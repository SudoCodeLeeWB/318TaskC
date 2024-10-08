package JOME.OrderService.application;


import JOME.OrderService.domain.entity.Customer;
import JOME.OrderService.domain.entity.Order;
import JOME.OrderService.domain.entity.Product;
import JOME.OrderService.domain.entity.ShoppingCart;
import JOME.OrderService.domain.factory.OrderFactory;
import JOME.OrderService.domain.factory.ShoppingCartFactory;
import JOME.OrderService.domain.service.OrderDomainService;
import JOME.OrderService.domain.service.ShoppingCartDomainService;
import JOME.OrderService.dto.OrderDTO;
import JOME.OrderService.dto.ShoppingCartDTO;
import JOME.OrderService.infrastructure.external.messaging.KafkaProducerService;
import JOME.OrderService.infrastructure.external.rest.RestPaymentClient;
import JOME.OrderService.infrastructure.persistance.CustomerRepostory;
import JOME.OrderService.infrastructure.persistance.OrderRepository;
import JOME.OrderService.infrastructure.persistance.ProductRepository;
import JOME.OrderService.infrastructure.persistance.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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
    public ShoppingCartDTO addNewOrderLineItemToShoppingCart(UUID customerId , UUID productId , int quantity) {

        // setup shopping cart
        Optional<ShoppingCart> recentShoppingCart = shoppingCartRepository.findByCustomerId(customerId);
        ShoppingCart currentShoppingCart = recentShoppingCart.orElseGet(() -> shoppingCartFactory.createNewShoppingCart(customerId));

        // get product data from productRepository, using productId
        Optional<Product> newProductOpt = productRepository.findById(productId);
        Product newProduct = newProductOpt.orElseThrow(()-> new RuntimeException()); // TODO

        // add product to the shopping cart ( delegate )
        ShoppingCart savedResult = shoppingCartDomainService.addNewProductToShoppingCart(currentShoppingCart, newProduct, quantity );
        savedResult = shoppingCartRepository.save(savedResult); // TODO

        return new ShoppingCartDTO(savedResult);
    }


    // 2. Change the Quantity of a OrderLineProduct ( shopping cart ) - Delete
    public ShoppingCartDTO deleteOrderLineItemQuantity( UUID customerId, UUID productId , int quantity ){

        //setup shopping cart
        Optional<ShoppingCart> recentShoppingCart = shoppingCartRepository.findByCustomerId(customerId);
        ShoppingCart currentShoppingCart = recentShoppingCart.orElseThrow(() -> new RuntimeException()); // TODO

        // delete product quantity from shopping cart ( delegate )
        ShoppingCart savedResult = shoppingCartDomainService.removeProductQuantityFromShoppingCart(currentShoppingCart,productId, quantity );
        savedResult = shoppingCartRepository.save(savedResult); // TODO
        return new ShoppingCartDTO(savedResult);

    }

    // 3. Change the Quantity of a OrderLineProduct ( shopping cart ) - Add

    public ShoppingCartDTO addOrderLineItemQuantity( UUID customerId , UUID productId , int quantity ){

        //setup shopping cart
        Optional<ShoppingCart> recentShoppingCart = shoppingCartRepository.findByCustomerId(customerId);
        ShoppingCart currentShoppingCart = recentShoppingCart.orElseThrow(() -> new RuntimeException()); // TODO

        // Add product quantity from shopping cart ( delegate )
        ShoppingCart savedResult = shoppingCartDomainService.addProductQuantityFromShoppingCart(currentShoppingCart,productId, quantity );
        savedResult = shoppingCartRepository.save(savedResult); // TODO
        return new ShoppingCartDTO(savedResult);

    }


    // 3. Place Order
    public Order placeOrder( UUID customerId ){

        // find Shopping Cart
        Optional<ShoppingCart> recentShoppingCart = shoppingCartRepository.findByCustomerId(customerId);
        ShoppingCart currentShoppingCart = recentShoppingCart.orElseThrow(() -> new RuntimeException()); // TODO

        // find customer information
        Optional<Customer> customer = customerRepostory.findById(customerId);
        Customer currentCustomer = customer.orElseThrow(() -> new RuntimeException()); // TODO

        // convert shopping cart into Order
        Order newOrder = orderFactory.createOrder(currentShoppingCart , currentCustomer);


        // process payment of this order
        boolean paymentResult = restPaymentClient.processPayment(currentCustomer);


        // Change the state of the Order - call orderDomain Service here



        // New Event : OrderPlaced Event

        // save the Order State
        Order SavedState = orderRepository.save(newOrder);
        return new OrderDTO(SavedState);

    }

        // Convert Shopping cart to Order
        // get delivery address from customerRepository
        // get payment success from restPaymentClient & change the payment status
        // if payment fail -> throw exception
        // change orderStauts ( if exception => fail ) / ( if no exception => true )
        // Event : new OrderPlaced Event
        // return success


    // 4. Cancel Order

        // change OrderStatus
        // Event : new OrderCanceled Event
        // return success




    // additional use case from others ( customer repository management )

    // Kafka subscribe

    // for CustomerRRepository - CRUD
//  use  @KafkaListener()
    public void handleCustomerCreated(){}
    public void handleCustomerDeleted(){}
    public void handleCustomerUpdated(){}

    // for ProductRepository - CRUD
    public void handleProductCreated(){}
    public void handleProductDeleted(){}
    public void handleProductUpdated(){}


}






