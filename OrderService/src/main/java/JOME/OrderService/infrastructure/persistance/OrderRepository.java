package JOME.OrderService.infrastructure.persistance;

import JOME.OrderService.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

// A repository interface to store Order Aggregate


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
