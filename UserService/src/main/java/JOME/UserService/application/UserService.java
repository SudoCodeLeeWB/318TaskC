package JOME.UserService.application;


import JOME.UserService.domain.entity.User;
import JOME.UserService.domain.factory.UserFactory;
import JOME.UserService.dto.UserDTO;
import JOME.UserService.infrastructure.persistance.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import JOME.UserService.infrastructure.external.messaging.KafkaProducerService;


@Service
public class UserService {

    

    // Repository
    private final UserRepository userRepository;

    // Factory
    private final UserFactory userFactory;

    // Messaging
    private final KafkaProducerService kafkaProducerService;


    @Autowired
    public UserService(UserRepository userRepository, UserFactory userFactory, KafkaProducerService kafkaProducerService) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
        this.kafkaProducerService = kafkaProducerService;
    }

//
//    // Create
//    public UserDTO addNewUser( UserDTO userDTO){
//
//
//        User newUser = userFactory.createUser(userDTO);
//
//
//        // save the new product into product repository
//        User savedResult = userRepository.save(newUser);
//
//        // for persistence -> when it is saved, then it will have a id,
//        // this id will be used for same in other databases ( persistence )
//
//        // Raise new event = AddNewProductEvent
//        AddNewProductEvent event = new AddNewProductEvent(savedResult);
//        kafkaProducerService.sendProductAddEvent(event);
//
//
//        return new ProductDTO(savedResult);
//
//    }




//    // Update
//    public ProductDTO updateProductStock( Long productId , int quantity ){
//
//        // find a same product in the product repository
//        Optional<Product> findProduct = productRepository.findById(productId);
//        Product targetProduct = findProduct.orElseThrow(()-> new RuntimeException());
//
//        // change the state
//        Product modifiedProduct = productDomainService.updateQuantity(targetProduct, quantity);
//
//        // save the new product into product repository
//        Product savedResult = productRepository.save(modifiedProduct);
//
//
//        // for persistence -> when it is saved, then it will have an id,
//        // this id will be used for same in other databases ( persistence )
//        // Raise New Event = UpdateProductStockEvent
//        UpdateProductStockEvent event = new UpdateProductStockEvent(savedResult);
//        kafkaProducerService.sendProductUpdateEvent(event);
//
//        return new ProductDTO(savedResult);
//
//    }
//
//
//
//    // Delete
//    public String deleteProduct(Long productId){
//
//        // find a same product in the product repository
//        Optional<Product> findProduct = productRepository.findById(productId);
//        Product targetProduct = findProduct.orElseThrow(()-> new RuntimeException());
//
//        // temp Name Storing for return resut & send message
//        String targetProductName = targetProduct.getName();
//        Long deletionId = targetProduct.getId();
//
//        // delete the product from product repository
//        productRepository.delete(targetProduct);
//
//
//        // Raise New Event : ProductDeleteEvent
//        ProductDeleteEvent event = new ProductDeleteEvent(deletionId);
//        kafkaProducerService.sendProductDeleteEvent(event);
//
//
//        return "Deleted Product : " + targetProductName ;
//
//    }
//

}


