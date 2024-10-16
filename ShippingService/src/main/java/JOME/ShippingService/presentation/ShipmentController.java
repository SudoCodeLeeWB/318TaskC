package JOME.ShippingService.presentation;

import JOME.ShippingService.domain.entity.Shipment;
import JOME.ShippingService.dto.ShipmentDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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


    // Getting shipment info by order ID
    @GetMapping("/getShippingStatus/{orderId}")
    ResponseEntity<ShipmentDTO> getShippingStatus(@PathVariable Long orderId) {

        ShipmentDTO response = shippingService.getShipmentByOrderID(orderId);
        if(response != null){
            return ResponseEntity.ok(response);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PatchMapping("/markAsShipped/{orderId}")
    ResponseEntity<ShipmentDTO> markAsShipped(@PathVariable Long orderId) {

        ShipmentDTO response = shippingService.markAsShipped(orderId);
        if(response != null){
            return ResponseEntity.ok(response);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PatchMapping("/markAsDelivered/{orderId}")
    ResponseEntity<ShipmentDTO> markAsDelivered(@PathVariable Long orderId) {

        ShipmentDTO response = shippingService.markAsDelivered(orderId);
        if(response != null){
            return ResponseEntity.ok(response);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }






}
