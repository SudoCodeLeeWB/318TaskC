// package JOME.ProductService.application;

// import JOME.ProductService.domain.entity.Product;
// import JOME.ProductService.domain.event.AddNewProductEvent;
// import JOME.ProductService.domain.event.ProductDeleteEvent;
// import JOME.ProductService.domain.event.UpdateProductStockEvent;
// import JOME.ProductService.domain.factory.ProductFactory;
// import JOME.ProductService.domain.service.ProductDomainService;
// import JOME.ProductService.domain.valueObject.CategoryEnum;
// import JOME.ProductService.dto.ProductDTO;
// import JOME.ProductService.infrastructure.external.messaging.KafkaProducerService;
// import JOME.ProductService.infrastructure.persistance.ProductRepository;

package JOME.UserService.application;



import org.springframework.stereotype.Service;



import java.util.Optional;

@Service
public class UserService {

    
    // Leaving here for @Owen

    // Repository
    private final ProductRepository productRepository;

    // Factory
    private final ProductFactory productFactory;

    // Domain Service
    private final ProductDomainService productDomainService;

    // Messaging
    private final KafkaProducerService kafkaProducerService;


    public UserService(ProductRepository productRepository, ProductFactory productFactory, ProductDomainService productDomainService, KafkaProducerService kafkaProducerService) {
        this.productRepository = productRepository;
        this.productFactory = productFactory;
        this.productDomainService = productDomainService;
        this.kafkaProducerService = kafkaProducerService;
    }


    // use case -> will not implement read - just automatically return the saved state as DTO



    // Create
    public ProductDTO addNewProduct( ProductDTO productDTO){


        // create a new product using ProductFactory -> no same Product in repo
        Product newProduct = productFactory.createProduct(productDTO); // handle null case later

        // save the new product into product repository
        Product savedResult = productRepository.save(newProduct);

        // for persistence -> when it is saved, then it will have a id,
        // this id will be used for same in other databases ( persistence )

        // Raise new event = AddNewProductEvent
        AddNewProductEvent event = new AddNewProductEvent(savedResult);
        kafkaProducerService.sendProductAddEvent(event);


        return new ProductDTO(savedResult);

    }




    // Update
    public ProductDTO updateProductStock( Long productId , int quantity ){

        // find a same product in the product repository
        Optional<Product> findProduct = productRepository.findById(productId);
        Product targetProduct = findProduct.orElseThrow(()-> new RuntimeException());

        // change the state
        Product modifiedProduct = productDomainService.updateQuantity(targetProduct, quantity);

        // save the new product into product repository
        Product savedResult = productRepository.save(modifiedProduct);


        // for persistence -> when it is saved, then it will have an id,
        // this id will be used for same in other databases ( persistence )
        // Raise New Event = UpdateProductStockEvent
        UpdateProductStockEvent event = new UpdateProductStockEvent(savedResult);
        kafkaProducerService.sendProductUpdateEvent(event);

        return new ProductDTO(savedResult);

    }



    // Delete
    public String deleteProduct(Long productId){

        // find a same product in the product repository
        Optional<Product> findProduct = productRepository.findById(productId);
        Product targetProduct = findProduct.orElseThrow(()-> new RuntimeException());

        // temp Name Storing for return resut & send message
        String targetProductName = targetProduct.getName();
        Long deletionId = targetProduct.getId();

        // delete the product from product repository
        productRepository.delete(targetProduct);


        // Raise New Event : ProductDeleteEvent
        ProductDeleteEvent event = new ProductDeleteEvent(deletionId);
        kafkaProducerService.sendProductDeleteEvent(event);


        return "Deleted Product : " + targetProductName ;

    }


}


