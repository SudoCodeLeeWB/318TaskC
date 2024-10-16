package JOME.UserService.domain.valueObject;

import jakarta.persistence.Embeddable;

@Embeddable
public class DeliveryAddress {


    private String street;
    private String state;
    private String country;
    private String postCode;


    protected DeliveryAddress(){}

    public DeliveryAddress(String street , String country, String state , String postCode){
        this.street = street;
        this.country = country;
        this.state = state;
        this.postCode = postCode;
    }


    public String getStreet() {
        return street;
    }

    public String getCountry() {
        return country;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getState() {
        return state;
    }



}

