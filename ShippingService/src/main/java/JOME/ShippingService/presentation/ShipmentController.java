package JOME.ShippingService.presentation;

import JOME.ShippingService.domain.entity.Shipment;
import JOME.ShippingService.dto.ShipmentDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import JOME.ShippingService.application.ShippingService;

import java.util.Optional;

@RestController
public class ShipmentController {

    private final ShippingService shippingService;

    @Autowired
    public ShipmentController(ShippingService _shippingService) {
        this.shippingService = _shippingService;
    }

    // CRUD USE CASES
    // Creating a new shipment
    @PatchMapping("/shipment")
    public ResponseEntity<ShipmentDTO> createNewShipment(@RequestParam Long orderID) {

        ShipmentDTO responce = shippingService.saveNewShipment(orderID);

        if (responce != null) {
            return ResponseEntity.ok(responce);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Getting shipment by ID
            // Code below is more of a test
    @GetMapping("/shipment/{orderId}")
    Optional<Shipment> getShipmentByID(@PathVariable Long orderId) {
        return Optional.ofNullable(shippingService.getShipmentByOrderID(orderId));
    }

    // Updating shipment status


    // Deleting a shipment

}
