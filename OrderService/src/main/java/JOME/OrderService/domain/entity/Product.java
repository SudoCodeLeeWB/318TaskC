package JOME.OrderService.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;


// will be saved to the repository

@Entity
public class Product {

    @Id
    private UUID id;  // same value from Product Microservice
    private String name;
    private double price;


    // To be used for JPA Table creating
    protected Product(){}


    // to be used for ACL
    public Product( UUID Id , String name , double price ){
        this.id = Id;
        this.name = name;
        this.price = price;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



}
