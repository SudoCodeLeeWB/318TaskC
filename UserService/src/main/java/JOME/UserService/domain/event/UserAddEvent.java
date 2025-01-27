package JOME.UserService.domain.event;

import JOME.UserService.domain.entity.User;
import JOME.UserService.domain.valueObject.DeliveryAddress;
import JOME.UserService.domain.valueObject.UserType;


public class UserAddEvent {

    private Long userID;
    private String name;
    private String email;
    private String phoneNumber;

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
        this.street = street;
        this.state = state;
        this.country = country;
        this.postCode = postCode;
    }

    public UserAddEvent(User user){
        this.userID=user.getUserID();
        this.name=user.getName();
        this.email=user.getEmail();
        this.phoneNumber=user.getPhoneNumber();
        this.userType=user.getUserType();
        this.street= user.getAddress().getStreet();
        this.state= user.getAddress().getState();
        this.country= user.getAddress().getCountry();
        this.postCode= user.getAddress().getPostCode();
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
