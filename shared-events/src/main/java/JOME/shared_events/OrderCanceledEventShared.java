package JOME.shared_events;

import java.time.LocalDateTime;

public class OrderCanceledEventShared {


    private Long id;
    private Long customerId;
    private String country;
    private double totalPrice;

    public OrderCanceledEventShared(){}


    // Will be used in the shared-event
    public OrderCanceledEventShared(Long id, Long customerId, String country, double totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
