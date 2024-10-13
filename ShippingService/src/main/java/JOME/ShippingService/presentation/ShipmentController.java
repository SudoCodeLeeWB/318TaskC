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
    @PatchMapping("/shipment/new/{orderID}")
    public ResponseEntity<ShipmentDTO> createNewShipment(@PathVariable Long orderID) {

        ShipmentDTO responce = shippingService.saveNewShipment(orderID);

        if (responce != null) {
            return ResponseEntity.ok(responce);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Getting shipment by order ID
    @GetMapping("/shipment/getByOrder/{orderId}")
    Optional<Shipment> getShipmentByID(@PathVariable Long orderId) {
        return Optional.ofNullable(shippingService.getShipmentByOrderID(orderId));
    }

    // Updating shipment status
    @PatchMapping("/shipment/updateStatus/{orderID}/{status}")
    public ResponseEntity<ShipmentDTO> updateShipmentStatus(@PathVariable Long orderID, @PathVariable String status) {
        ShipmentDTO response = shippingService.updateShipmentStatus(orderID, status);

        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Deleting a shipment
    @DeleteMapping("/shipment/delete/{orderID}")
    public ResponseEntity<String> deleteShipment(@PathVariable Long orderID) {
        try {
            shippingService.deleteShipment(orderID);
            return ResponseEntity.ok("Shipment deleted successfully");
        } catch (RuntimeException e) {
            return new ResponseEntity<>("cannot delete a shipped shipment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
