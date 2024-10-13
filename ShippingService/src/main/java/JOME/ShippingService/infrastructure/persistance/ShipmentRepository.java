package JOME.ShippingService.infrastructure.persistance;

import JOME.ShippingService.domain.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    Shipment findByOrderID(Long orderID);
}
