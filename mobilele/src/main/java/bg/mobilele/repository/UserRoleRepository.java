package bg.mobilele.repository;

import bg.mobilele.model.entity.User;
import bg.mobilele.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Locale;
import java.util.Set;
import java.util.UUID;
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
