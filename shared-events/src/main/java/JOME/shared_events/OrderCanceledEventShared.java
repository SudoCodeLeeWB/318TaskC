package JOME.shared_events;

import java.time.LocalDateTime;

public class OrderCanceledEventShared {


    private Long id;
    private Long customerId;

    private LocalDateTime recentUpdateTime;
    private LocalDateTime eventCreateTime;

    private double totalPrice;


    public OrderCanceledEventShared(){}

    // Will be used in the shared-event
    public OrderCanceledEventShared(Long id, Long customerId, LocalDateTime recentUpdateTime,
                                    LocalDateTime eventCreateTime, double totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.recentUpdateTime = recentUpdateTime;
        this.eventCreateTime = eventCreateTime;
        this.totalPrice = totalPrice;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
