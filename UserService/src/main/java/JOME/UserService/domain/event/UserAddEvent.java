package JOME.UserService.domain.event;

import JOME.UserService.domain.entity.User;
import JOME.UserService.domain.valueObject.DeliveryAddress;
import JOME.UserService.domain.valueObject.UserType;


public class UserAddEvent {

    public Long userID;
    public String name;
    public String email;
    public String phoneNumber;

    // value objects
    public UserType userType;

    private String street;
    private String state;
    private String country;
    private String postCode;


    public UserAddEvent(Long userID, String name, String email, String phoneNumber, UserType userType , String street, String state, String country, String postCode) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.


    }


    public UserAddEvent(User user){

        this.userID=user.getUserID();
        this.name=user.getName();
        this.email=user.getEmail();
        this.phoneNumber=user.getPhoneNumber();
        this.userType=user.getUserType();
        this.

    }



}
