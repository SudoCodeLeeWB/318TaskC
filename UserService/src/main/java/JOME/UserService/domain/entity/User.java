package JOME.UserService.domain.entity;

import jakarta.persistence.*;
import java.util.List;
import org.springframework.stereotype.Component;
import JOME.UserService.domain.valueObject.Address;
import JOME.UserService.domain.enumeration.UserType;

// make UserType, Cart, Order


@Entity
@Component
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userID;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;

    // value objects
    @Enumerated
    private UserType userType;

    @Embedded
    private Address address;

    private Cart cart;
    private List<Order> orders;


    // Shipment constructor to initialise a shippingStatus, order ID
    public User (){}
    public User(String userID, String name, String email, String phoneNumber, String password, UserType userType, Cart cart, List<Order> orders, Address address ) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userType = userType;
        this.cart = cart;
        this.address = address;
    }

    // getters
    public String getUserID(){
        return this.userID;
    }
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
    public Cart getCart(){
        return this.cart;
    }
    public List<Order> getOrders(){
        return this.orders;
    }
    public Address getAddress(){
        return this.address;
    }

    // setters
    public void setUserID(String userID){
        this.userID = userID;
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
    public void setCart(Cart cart){
        this.cart = cart;
    }
    public void setOrders(List<Order> orders){
        this.orders = orders;
    }
    public void setAddress(Address address){
        this.address = address;
    }

    // update list / cart
    public void updateOrders(Order newOrder){
        this.orders.add(newOrder);
    }

}
