package JOME.OrderService.domain.event;


import JOME.OrderService.domain.entity.Order;

import java.time.LocalDateTime;

public class OrderCanceledEvent {


    private Long id;
    private Long customerId;

    private LocalDateTime recentUpdateTime;
    private LocalDateTime eventCreateTime;

    private double totalPrice;

    public OrderCanceledEvent(){}

    // Will be used for application layer
    public OrderCanceledEvent(Order order){
        this.id = order.getId();
        this.customerId = order.getCustomerId();
        this.recentUpdateTime = order.getRecentUpdateTime();
        this.eventCreateTime = LocalDateTime.now();
        this.totalPrice = order.getTotalPrice();
    }

    // Will be used in the shared-event
    public OrderCanceledEvent(Long id, Long customerId, LocalDateTime recentUpdateTime,
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
