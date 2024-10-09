package JOME.ShippingService.repository;

import JOME.ShippingService.domain.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
