package JOME.ShippingService.application;

import JOME.ShippingService.domain.entity.Shipment;
import JOME.ShippingService.domain.valueObject.ShippingStatus;
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
    public ShipmentDTO updateShipmentStatus(Long _orderID, String _status) {
        Shipment shipment = shipmentRepository.findByOrderID(_orderID);
        if (shipment == null) {
            return null;
        }
        if (_status.equals("SHIPPED") && shipment.getShippingStatus().equals(ShippingStatus.NOT_SHIPPED)) {
            shipment.setToShipped();
        } else if (_status.equals("DELIVERED") && shipment.getShippingStatus().equals(ShippingStatus.SHIPPED)) {
            shipment.setToDelivered();

            // TODO: Notify the order service that the order has been delivered
        } else {
            return null;
        }
        shipmentRepository.save(shipment);
        return new ShipmentDTO(shipment);
    }

    // Delete a shipment if it is not yet shipped
    public void deleteShipment(Long _orderID) {
        Shipment shipment = shipmentRepository.findByOrderID(_orderID);
        if (shipment.getShippingStatus().equals(ShippingStatus.NOT_SHIPPED)) {
            shipmentRepository.delete(shipment);
        } else {
            throw new RuntimeException();
        }
    }
}
