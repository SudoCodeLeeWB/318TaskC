package JOME.ShippingService.presentation;

import JOME.ShippingService.domain.valueObject.DeliveryAddress;
import JOME.ShippingService.repository.ShipmentRepository;
import JOME.ShippingService.domain.entity.Shipment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import JOME.ShippingService.application.ShippingService;

import java.util.Optional;

@RestController
public class ShipmentController {
    private final ShipmentRepository shipmentRepository;

    private ShippingService shippingService;

    public ShipmentController(ShipmentRepository _shipmentRepository) {
        this.shipmentRepository = _shipmentRepository;
    }

    // Creating a new shipment
    @PostMapping("/shipment")
    Shipment createShipment(@RequestParam Long orderID, @RequestBody DeliveryAddress _deliveryAddress) {
        return shippingService.saveNewShipment(orderID, _deliveryAddress);
    }

    // Getting shipment by ID
    // Code below is just a test
    @GetMapping("/shipment/{id}")
    Optional<Shipment> getShipmentByID(@PathVariable Long id) {
        return shipmentRepository.findById(id);
    }
}
