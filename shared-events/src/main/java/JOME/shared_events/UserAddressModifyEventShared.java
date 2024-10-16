package JOME.shared_events;

public class UserAddressModifyEventShared {


    private Long userID;
    private String street;
    private String state;
    private String country;
    private String postCode;


    public UserAddressModifyEventShared() {}

    // will be used for shared event
    public UserAddressModifyEventShared(Long userID, String street, String state, String country, String postCode) {
        this.userID = userID;
        this.street = street;
        this.state = state;
        this.country = country;
        this.postCode = postCode;
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
