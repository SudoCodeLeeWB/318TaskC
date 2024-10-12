package JOME.shared_events;


import java.time.LocalDateTime;


public class OrderPlacedEventShared {

    private Long id;
    private Long customerId;
    private String customerName;
    private double totalPrice;
    private LocalDateTime recentUpdateTime;
    private LocalDateTime eventCreateTime;

    // Delivery Address Part
    private String street;
    private String state;
    private String country;
    private String postCode;


    // Constructor
    public OrderPlacedEventShared(){}



    // Will be used for shared-event
    // Base constructor
    public OrderPlacedEventShared(Long id, Long customerId, String customerName, double totalPrice,
                                  LocalDateTime recentUpdateTime, LocalDateTime eventCreateTime,
                                  String street, String state, String country, String postCode) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
        this.recentUpdateTime = recentUpdateTime;
        this.eventCreateTime = eventCreateTime;
        this.street = street;
        this.state = state;
        this.country = country;
        this.postCode = postCode;
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

    public String getCustomerName() {
        return customerName;
    }



    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
