package bg.mobilele.repository;

import bg.mobilele.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    Offer findById(long offerId);
}
