package JOME.ShippingService.dto;

import JOME.ShippingService.domain.entity.Shipment;
import JOME.ShippingService.domain.valueObject.ShippingStatus;

public class ShipmentDTO {

    public Long orderID;
    public ShippingStatus shippingStatus;

    public ShipmentDTO() {}

    public ShipmentDTO(Shipment shipment) {
        this.orderID = shipment.getOrderID();
        this.shippingStatus = shipment.getShippingStatus();
    }


}
