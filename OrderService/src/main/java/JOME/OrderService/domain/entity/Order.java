package JOME.OrderService.domain.entity;

import JOME.OrderService.domain.valueObject.DeliveryAddress;
import JOME.OrderService.domain.valueObject.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


// Order -> Root Aggregate
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private String customerName;
    private boolean paymentStatus;
    private double totalPrice;
    private LocalDateTime recentUpdateTime;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
    private List<OrderOrderLineItem> orderLineItemList = new ArrayList<>();

    // value objects
    @Enumerated
    private OrderStatus orderStatus;

    @Embedded
    private DeliveryAddress deliveryAddress;


    // Constructor

    // For Serializing -> H2
    public Order(){}


    /**
     * Will be used for OrderFactory
     * */
    public Order(Long customerId, boolean paymentStatus, double totalPrice, OrderStatus orderStatus, DeliveryAddress deliveryAddress , List<OrderOrderLineItem> orderLineItemList , String customerName , LocalDateTime recentUpdateTime){
        this.customerId = customerId;
        this.paymentStatus = paymentStatus;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
        this.recentUpdateTime = recentUpdateTime;
        this.orderLineItemList = orderLineItemList;
        this.customerName = customerName;

    }

    // Order address of OrderLineItemList ( Factory )
    public void orderSetter( Long customerId, boolean paymentStatus, double totalPrice, OrderStatus orderStatus, DeliveryAddress deliveryAddress , List<OrderOrderLineItem> orderLineItemList , String customerName , LocalDateTime recentUpdateTime){
        this.customerId = customerId;
        this.paymentStatus = paymentStatus;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.deliveryAddress = deliveryAddress;
        this.recentUpdateTime = recentUpdateTime;
        this.orderLineItemList = orderLineItemList;
        this.customerName = customerName;
    }


    /**
     * Updates the recent aggregate modified time
     */
    public void updateRecentUpdateTime(){
        this.recentUpdateTime = LocalDateTime.now();
    }


    /**
     * Mark the OrderStatus As Completed
    */
    public void completeOrder(){
        this.orderStatus = OrderStatus.COMPLETED;
    }


    /**
     * Mark the OrderStatus As Failed
     */
    public void failedOrder(){
        this.orderStatus = OrderStatus.FAILED;
    }

    /**
     * Mark the OrderStatus As Failed
     */
    public void cancelOrder(){
        this.orderStatus = OrderStatus.CANCELED;
    }


    /**
     * Mark the paymentStatus True
     */
    public void markPaymentAsDone(){
        this.paymentStatus = true;
    }




    // getters and setters
    public boolean isPaymentDone(){
        return paymentStatus;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getRecentUpdateTime() {
        return recentUpdateTime;
    }

    public void setRecentUpdateTime(LocalDateTime recentUpdateTime) {
        this.recentUpdateTime = recentUpdateTime;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<OrderOrderLineItem> getOrderLineItemList() {
        return orderLineItemList;
    }

    public void setOrderLineItemList(List<OrderOrderLineItem> orderLineItemList) {
        this.orderLineItemList = orderLineItemList;
    }
}
