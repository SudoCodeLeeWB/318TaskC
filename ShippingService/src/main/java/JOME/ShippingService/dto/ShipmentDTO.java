package JOME.ShippingService.dto;

import JOME.ShippingService.domain.entity.Shipment;
import JOME.ShippingService.domain.valueObject.DeliveryAddress;
import JOME.ShippingService.domain.valueObject.ShippingStatus;

public class ShipmentDTO {

    public Long orderID;
    public ShippingStatus shippingStatus;
    public DeliveryAddress deliveryAddress;

    public ShipmentDTO() {}

    public ShipmentDTO(Shipment shipment) {
        this.orderID = shipment.getOrderID();
        this.shippingStatus = shipment.getShippingStatus();
        this.deliveryAddress = shipment.getDeliveryAddress();
    }


}
