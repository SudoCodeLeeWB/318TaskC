package JOME.UserService.domain.event;

import JOME.UserService.domain.entity.User;
import JOME.UserService.domain.valueObject.UserType;

public class UserAddressModifyEvent {


    private Long userID;
    private String street;
    private String state;
    private String country;
    private String postCode;


    // will be used for shared event
    public UserAddressModifyEvent(Long userID, String street, String state, String country, String postCode) {
        this.userID = userID;
        this.street = street;
        this.state = state;
        this.country = country;
        this.postCode = postCode;
    }

    public UserAddressModifyEvent(User user) {
        this.userID = user.getUserID();
        this.street = user.getAddress().getStreet();
        this.state = user.getAddress().getState();
        this.country = user.getAddress().getCountry();
        this.postCode = user.getAddress().getPostCode();
    }


    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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
}
