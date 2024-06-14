package bg.mobilele.repository;

import bg.mobilele.model.entity.Brand;
import bg.mobilele.model.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    Model findModelByModelName(String modelName);
}
