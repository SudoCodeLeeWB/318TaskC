package JOME.ShippingService.presentation;

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

    public ShipmentController(ShipmentRepository _shipmentRepository, ShippingService _shippingService) {
        this.shipmentRepository = _shipmentRepository;
        this.shippingService = _shippingService;
    }

    // CRUD USE CASES
    // Creating a new shipment
    @PostMapping("/shipment")
    Shipment createShipment(@RequestParam Long orderID) {
        return shippingService.saveNewShipment(orderID);
    }

    // Getting shipment by ID
    // Code below is just a test
    @GetMapping("/shipment/{id}")
    Optional<Shipment> getShipmentByID(@PathVariable Long id) {
        return shipmentRepository.findById(id);
    }

    // Updating shipment status

    // Deleting a shipment

}
