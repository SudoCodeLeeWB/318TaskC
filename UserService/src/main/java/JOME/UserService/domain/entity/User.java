package JOME.UserService.domain.entity;

import JOME.UserService.domain.valueObject.DeliveryAddress;
import jakarta.persistence.*;
import JOME.UserService.domain.valueObject.UserType;


@Entity
@Table(name="Sys_User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    private String name;
    private String email;
    private String phoneNumber;
    private String password;

    // value objects
    @Enumerated
    private UserType userType;

    @Embedded
    private DeliveryAddress address;


    // Shipment constructor to initialise a shippingStatus, order ID
    public User (){}
    public User( String name, String email, String phoneNumber, String password, UserType userType, DeliveryAddress address ) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userType = userType;
        this.address = address;
    }


    // getters
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public String getPassword(){
        return this.password;
    }
    public UserType getUserType(){
        return this.userType;
    }


    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public void setMobileNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setUserType(UserType userType){
        this.userType = userType;
    }


    public DeliveryAddress getAddress() {
        return address;
    }

    public void setAddress(DeliveryAddress address) {
        this.address = address;
    }
}
