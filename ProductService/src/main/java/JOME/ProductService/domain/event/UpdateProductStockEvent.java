package JOME.ProductService.domain.event;

import JOME.ProductService.domain.entity.Product;
import JOME.ProductService.domain.valueObject.CategoryEnum;

import java.time.LocalDateTime;

public class UpdateProductStockEvent {


    private Long Id;
    private int stock;
    private LocalDateTime recentUpdateTime;
    private LocalDateTime eventCreateTime;



    public UpdateProductStockEvent(){}


    public UpdateProductStockEvent(Product product){

        this.Id = product.getId();
        this.stock = product.getStock();
        this.recentUpdateTime = product.getRecentUpdateTime();
        this.eventCreateTime = LocalDateTime.now();

    }



    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public LocalDateTime getEventCreateTime() {
        return eventCreateTime;
    }

    public void setEventCreateTime(LocalDateTime eventCreateTime) {
        this.eventCreateTime = eventCreateTime;
    }
}
