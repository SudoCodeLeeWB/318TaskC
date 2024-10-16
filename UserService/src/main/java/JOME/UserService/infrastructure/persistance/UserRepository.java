package JOME.UserService.infrastructure.persistance;

import JOME.UserService.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
