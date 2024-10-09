package JOME.OrderService.infrastructure.persistance;

import JOME.OrderService.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

// A repository to store the product data
// managed by ShippingService Event => Subscribe


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
