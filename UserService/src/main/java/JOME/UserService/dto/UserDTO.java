package JOME.UserService.dto;
import JOME.UserService.domain.entity.User;
import JOME.UserService.domain.valueObject.DeliveryAddress;
import JOME.UserService.domain.valueObject.UserType;
import jakarta.persistence.*;

public class UserDTO {

    public String name;
    public String email;
    public String phoneNumber;
    public String password;
    public UserType userType;
    public DeliveryAddress address;


    public UserDTO(){};

    // out
    public UserDTO(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.password = "*****";  // do not expose password
        this.userType = user.getUserType();
        this.address = user.getAddress();
    }




}
