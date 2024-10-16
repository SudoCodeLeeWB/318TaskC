package JOME.UserService.domain.factory;


import JOME.UserService.domain.entity.User;
import JOME.UserService.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public User createUser( UserDTO userDTO){

        // constraints for user

        // password Must not be empty ( example )
        if(userDTO.password.isEmpty()){
            return null;
        }


        return new User(
                userDTO.name,
                userDTO.email,
                userDTO.phoneNumber,
                userDTO.password,
                userDTO.userType,
                userDTO.address
        );

    }




}
