package JOME.OrderService.infrastructure.persistance;

import JOME.OrderService.domain.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


// A repository to store ShoppingCart Entity ( temp )


@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart , UUID> {
    Optional<ShoppingCart> findByCustomerId(UUID customerId);

}
