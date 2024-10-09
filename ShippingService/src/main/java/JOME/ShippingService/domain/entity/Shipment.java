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
    @Embedded
    private ShippingStatus shippingStatus;
    private Long orderID;

    @Embedded
    private DeliveryAddress deliveryAddress;


    // Shipment constructor to initialise a shippingStatus, order ID and delivery address
    public Shipment (){}
    public Shipment(Long _orderID, DeliveryAddress _deliveryAddress) {
        this.shippingStatus = ShippingStatus.NOT_SHIPPED;
        this.orderID = _orderID;
        this.deliveryAddress = _deliveryAddress;
    }

    // Function to get the ID of the shipment
    public Long getShipmentID() {
        return shipmentID;
    }

    // Function to get the ID of the order associated with the shipment
    public Long getOrderID() {
        return orderID;
    }

    // Function to get the status of the Shipment
    public ShippingStatus getShippingStatus() {return shippingStatus;}

    // Functions to set the Shipment Status of the shipment
    public void setToShipped() {
        if (this.shippingStatus == ShippingStatus.NOT_SHIPPED) {
            this.shippingStatus = ShippingStatus.SHIPPED;
        } else {
            throw new RuntimeException(); // TODO: HAVE MESSAGE
        }
    }
    public void setToDelivered() {
        if (this.shippingStatus == ShippingStatus.SHIPPED) {
            this.shippingStatus = ShippingStatus.DELIVERED;
        } else {
            throw new RuntimeException(); // TODO: HAVE MESSAGE
        }
    }

    // Function to get the delivery address of the shipment
    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }
}
