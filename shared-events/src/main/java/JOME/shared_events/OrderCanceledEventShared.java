package JOME.shared_events;

import java.time.LocalDateTime;

public class OrderCanceledEventShared {


    private Long id;
    private Long customerId;


    private double totalPrice;


    public OrderCanceledEventShared(){}

    // Will be used in the shared-event
    public OrderCanceledEventShared(Long id, Long customerId, double totalPrice) {
        this.id = id;
        this.customerId = customerId;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
