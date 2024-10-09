package JOME.ProductService.domain.event;

import JOME.ProductService.domain.entity.Product;
import JOME.ProductService.domain.valueObject.CategoryEnum;

import java.time.LocalDateTime;

public class AddNewProductEvent {

    private Long Id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private CategoryEnum category;
    private LocalDateTime recentUpdateTime;
    private LocalDateTime eventCreateTime;


    // constructor for translating the Product
    public AddNewProductEvent() {}

    public AddNewProductEvent(Product product) {

        this.Id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.category = product.getCategory();
        this.recentUpdateTime = product.getRecentUpdateTime();
        this.eventCreateTime = LocalDateTime.now();

    }


    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public LocalDateTime getRecentUpdateTime() {
        return recentUpdateTime;
    }

    public void setRecentUpdateTime(LocalDateTime recentUpdateTime) {
        this.recentUpdateTime = recentUpdateTime;
    }


    public LocalDateTime getEventCreateTime() {
        return eventCreateTime;
    }

    public void setEventCreateTime(LocalDateTime eventCreateTime) {
        this.eventCreateTime = eventCreateTime;
    }
}

