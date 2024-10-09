package JOME.ShippingService.application;

import JOME.ShippingService.domain.valueObject.DeliveryAddress;

import JOME.ShippingService.domain.entity.Shipment;
import JOME.ShippingService.repository.ShipmentRepository;

import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    private final DeliveryAddress deliveryAddress;
    private final Shipment shipment;
    private final ShipmentRepository shipmentRepository;

    public ShippingService(DeliveryAddress _deliveryAddress, Shipment _shipment, ShipmentRepository _shipmentRepository, DeliveryAddress deliveryAddress, Shipment shipment) {
        this.deliveryAddress = _deliveryAddress;
        this.shipment = _shipment;
        this.shipmentRepository = _shipmentRepository;
    }

    public Shipment saveNewShipment(Long _orderID, DeliveryAddress _deliveryAddress) {
        Shipment newShipment = new Shipment(_orderID, _deliveryAddress);

        shipmentRepository.save(newShipment);
        return newShipment;
    }
}
