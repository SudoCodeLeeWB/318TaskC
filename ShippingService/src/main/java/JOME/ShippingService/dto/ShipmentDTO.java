package JOME.ShippingService.dto;

import JOME.ShippingService.domain.entity.Shipment;
import JOME.ShippingService.domain.valueObject.ShippingStatus;

public class ShipmentDTO {

    public ShippingStatus shippingStatus;
    public Long orderID;

    public ShipmentDTO() {}

    public ShipmentDTO(Shipment shipment) {
        this.shippingStatus = shipment.getShippingStatus();
        this.orderID = shipment.getOrderID();
    }


}
