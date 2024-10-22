package bg.mobilele.repository;

import bg.mobilele.model.entity.User;
import bg.mobilele.model.entity.UserRole;
import bg.mobilele.model.enums.Role;
import jakarta.persistence.Enumerated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findAllById(long id);
    UserRole findByRole(Role role);
}
