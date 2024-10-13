package JOME.ShippingService.application;

import JOME.ShippingService.domain.entity.Shipment;
import JOME.ShippingService.repository.ShipmentRepository;
import JOME.ShippingService.dto.ShipmentDTO;

import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    private final Shipment shipment;
    private final ShipmentRepository shipmentRepository;

    public ShippingService(Shipment _shipment, ShipmentRepository _shipmentRepository) {
        this.shipment = _shipment;
        this.shipmentRepository = _shipmentRepository;
    }

    // CRUD USE CASES
    // Create a new shipment
    public ShipmentDTO saveNewShipment(Long _orderID) {
        Shipment newShipment = new Shipment(_orderID);
        shipmentRepository.save(newShipment);
        return new ShipmentDTO(newShipment);
    }

    // Read a shipment by order ID
    public Shipment getShipmentByOrderID(Long _orderID) {
        return shipmentRepository.findByOrderID(_orderID);
    }

    // Update a shipment status

    // Delete a shipment if it is not yet shipped
}
