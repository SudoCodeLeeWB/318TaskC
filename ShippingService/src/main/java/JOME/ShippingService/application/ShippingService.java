package JOME.ShippingService.application;

import JOME.ShippingService.domain.entity.Shipment;
import JOME.ShippingService.repository.ShipmentRepository;

import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    private final Shipment shipment;
    private final ShipmentRepository shipmentRepository;

    public ShippingService(Shipment _shipment, ShipmentRepository _shipmentRepository) {
        this.shipment = _shipment;
        this.shipmentRepository = _shipmentRepository;
    }

    public Shipment saveNewShipment(Long _orderID) {
        Shipment newShipment = new Shipment(_orderID);
        shipmentRepository.save(newShipment);
        return newShipment;
    }

    // CRUD USE CASES
    // Create a new shipment

    // Read a shipment by ID

    // Update a shipment status

    // Delete a shipment if it is not yet shipped
}
