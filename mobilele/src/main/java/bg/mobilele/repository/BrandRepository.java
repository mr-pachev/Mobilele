package bg.mobilele.repository;

import bg.mobilele.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
