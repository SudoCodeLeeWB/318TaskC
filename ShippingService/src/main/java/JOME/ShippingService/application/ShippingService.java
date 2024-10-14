package JOME.ShippingService.application;

import JOME.ShippingService.antiCorruption.OrderCanceledEventMapper;
import JOME.ShippingService.antiCorruption.OrderPlacedEventMapper;
import JOME.ShippingService.domain.entity.Shipment;
import JOME.ShippingService.domain.service.ShipmentDomainService;
import JOME.ShippingService.infrastructure.persistance.ShipmentRepository;
import JOME.ShippingService.dto.ShipmentDTO;

import JOME.shared_events.OrderCanceledEventShared;
import JOME.shared_events.OrderPlacedEventShared;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    private final ShipmentRepository shipmentRepository;
    private final ShipmentDomainService shipmentDomainService;


    @Autowired
    public ShippingService(ShipmentRepository _shipmentRepository , ShipmentDomainService shipmentDomainService) {
        this.shipmentRepository = _shipmentRepository;
        this.shipmentDomainService = shipmentDomainService;
    }


    public ShipmentDTO getShipmentByOrderID(Long orderID){

        Shipment shipment = shipmentRepository.findByOrderID(orderID).orElseThrow(RuntimeException::new);
        return new ShipmentDTO(shipment);

    }

    public ShipmentDTO markAsShipped(Long orderID){

        // find the shipment in the repository
        Shipment shipment = shipmentRepository.findByOrderID(orderID).orElseThrow(RuntimeException::new);

        // modify the state of the shipment
        Shipment modifiedShipment = shipmentDomainService.markAsShipped(shipment);

        // save the changed state
        modifiedShipment = shipmentRepository.save(modifiedShipment);

        return new ShipmentDTO(modifiedShipment);

    }


    public ShipmentDTO markAsDelivered(Long orderID){

        // find the shipment in the repository
        Shipment shipment = shipmentRepository.findByOrderID(orderID).orElseThrow(RuntimeException::new);

        // modify the state of the shipment
        Shipment modifiedShipment = shipmentDomainService.markAsDelivered(shipment);

        // save the changed state
        modifiedShipment = shipmentRepository.save(modifiedShipment);

        return new ShipmentDTO(modifiedShipment);

    }


    // handled by event
    public void handleOrderPlacedEvent(OrderPlacedEventShared event ){

        // Apply ACL
        Shipment newShipment = OrderPlacedEventMapper.mapFromEventToDomain(event);

        // save the new Shipment
        shipmentRepository.save(newShipment);

    }

    public void handleOrderCanceledEvent(OrderCanceledEventShared event ){

        // Apply ACL
        Long targetId = OrderCanceledEventMapper.mapFromEventToDomain(event);

        // delete the order
        shipmentRepository.deleteById(targetId);

    }


}
