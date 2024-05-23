package bg.mobilele.repository;

import bg.mobilele.model.entity.User;
import bg.mobilele.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByUsername(String username);
}
