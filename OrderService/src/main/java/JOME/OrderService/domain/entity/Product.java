package JOME.OrderService.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;


// will be saved to the repository

@Entity
public class Product {

    @Id
    private Long id;  // same value from Product Microservice
    private String name;
    private double price;
    private int stock;
    private LocalDateTime recentUpdateTime;


    // To be used for JPA Table creating
    protected Product(){}


    // to be used for ACL
    public Product( Long Id , String name , double price , int stock , LocalDateTime recentUpdateTime){
        this.id = Id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.recentUpdateTime = recentUpdateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
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


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDateTime getRecentUpdateTime() {
        return recentUpdateTime;
    }

    public void setRecentUpdateTime(LocalDateTime recentUpdateTime) {
        this.recentUpdateTime = recentUpdateTime;
    }
}
