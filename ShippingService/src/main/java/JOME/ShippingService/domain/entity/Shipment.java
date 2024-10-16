package JOME.ShippingService.domain.entity;

import JOME.ShippingService.domain.valueObject.DeliveryAddress;
import JOME.ShippingService.domain.valueObject.ShippingStatus;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;


@Entity
@Component
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipmentID;
    private Long orderID;

    @Enumerated
    private ShippingStatus shippingStatus;

    @Embedded
    private DeliveryAddress deliveryAddress;


    // Shipment constructor to initialise a shippingStatus, order ID
    public Shipment (){}
    public Shipment(Long _orderID , DeliveryAddress deliveryAddress) {
        this.orderID = _orderID;
        this.shippingStatus = ShippingStatus.NOT_SHIPPED;
        this.deliveryAddress = deliveryAddress;

    }

    public void setToShipped(){
        this.shippingStatus = ShippingStatus.SHIPPED;
    }

    public void setToDelivered(){
        this.shippingStatus = ShippingStatus.DELIVERED;
    }



    // getters / setters
    public Long getShipmentID() {
        return shipmentID;
    }

    // Function to get the ID of the order associated with the shipment
    public Long getOrderID() {
        return orderID;
    }

    // Function to get the status of the Shipment
    public ShippingStatus getShippingStatus() {return shippingStatus;}


    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }





}
