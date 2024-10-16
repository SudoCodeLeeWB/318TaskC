package JOME.UserService.domain.factory;


import JOME.UserService.domain.entity.User;
import JOME.UserService.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {


    public User createUser( UserDTO userDTO){




        return new User();

    }




}
