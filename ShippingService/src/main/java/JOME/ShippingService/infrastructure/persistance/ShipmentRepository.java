package JOME.ShippingService.infrastructure.persistance;

import JOME.ShippingService.domain.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    Optional<Shipment> findByOrderID(Long orderID);
}
