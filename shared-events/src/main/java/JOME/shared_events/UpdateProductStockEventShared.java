package JOME.shared_events;

import java.time.LocalDateTime;

public class UpdateProductStockEventShared {


    private Long Id;
    private int stock;
    private LocalDateTime recentUpdateTime;
    private LocalDateTime eventCreateTime;



    public UpdateProductStockEventShared(){}


    public UpdateProductStockEventShared(Long id, int stock, LocalDateTime recentUpdateTime) {
        this.Id = id;
        this.stock = stock;
        this.recentUpdateTime = recentUpdateTime;
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
