package JOME.OrderService.infrastructure.persistance;

import JOME.OrderService.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

// A repository to store customer data
// managed by event from UserService

@Repository
public interface CustomerRepostory extends JpaRepository<Customer , UUID> {

}
