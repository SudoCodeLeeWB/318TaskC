package JOME.UserService.application;


import JOME.UserService.domain.entity.User;
import JOME.UserService.domain.event.UserAddEvent;
import JOME.UserService.domain.event.UserAddressModifyEvent;
import JOME.UserService.domain.factory.UserFactory;
import JOME.UserService.domain.service.UserDomainService;
import JOME.UserService.domain.valueObject.DeliveryAddress;
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

    private final UserDomainService userDomainService;

    @Autowired
    public UserService(UserRepository userRepository, UserFactory userFactory, KafkaProducerService kafkaProducerService, UserDomainService userDomainService) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
        this.kafkaProducerService = kafkaProducerService;
        this.userDomainService = userDomainService;
    }


    // Create
    public UserDTO addNewUser( UserDTO userDTO ){


        User newUser = userFactory.createUser(userDTO);

        // save the new product into product repository
        User savedResult = userRepository.save(newUser);

        // for persistence -> when it is saved, then it will have an id,
        // this id will be used for same in other databases ( persistence )


        // Raise new event = AddNewProductEvent
        UserAddEvent event = new UserAddEvent(savedResult);
        kafkaProducerService.sendUserAddEvent(event);


        return new UserDTO(savedResult);

    }



    public UserDTO updateUserAddress (Long id,  DeliveryAddress address ){


        User savedUser = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException());

        savedUser = userDomainService.modifyAddress(savedUser, address);
        savedUser = userRepository.save(savedUser);

        // Raise New Event :
        UserAddressModifyEvent event = new UserAddressModifyEvent(savedUser);
        kafkaProducerService.sendUserAddressModifiedEvent(event);


        return new UserDTO(savedUser);

    }



}


