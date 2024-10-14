package JOME.UserService.domain.valueObject;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // how to do tuple/tripple for ID? ask owen
    private Integer houseNumber;
    private String street;
    private String city;
    private String state;
    private String zipcode;

    // constructors
    public Address(){};
    public Address(Integer houseNumber, String street, String city, String state, String zipcode){
       this.houseNumber = houseNumber;
       this.street = street;
       this.city = city;
       this.state = state;
       this.zipcode = zipcode;

    }

    // getters
    public Integer getHouseNumber(){
        return this.houseNumber;
    }
    public String getStreet(){
        return this.street;
    }
    public String getCity(){
        return this.city;
    }
    public String getState(){
        return this.state;
    }
    public String getZipCode(){
        return this.zipcode;
    }

    // Setters
    public void setHouseNumber(Integer houseNumber){
        this.houseNumber = houseNumber;
    }
    public void setStreet(String street){
        this.street = street;
    }
    public void setCity(String city){
        this.street = city;
    }
    public void setState(String state){
        this.street = state;
    }
    public void setZipcode(String zipcode){
        this.street = zipcode;
    }
}
