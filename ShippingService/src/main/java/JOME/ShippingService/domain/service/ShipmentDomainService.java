package JOME.ShippingService.domain.service;

import JOME.ShippingService.domain.entity.Shipment;
import org.springframework.stereotype.Service;

@Service
public class ShipmentDomainService {


    public Shipment markAsShipped(Shipment shipment) {
        shipment.setToShipped();
        return shipment;
    }

    public Shipment markAsDelivered(Shipment shipment) {

        shipment.setToDelivered();
        return shipment;
    }

}
