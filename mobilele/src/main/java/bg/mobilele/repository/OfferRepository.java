package bg.mobilele.repository;

import bg.mobilele.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    Optional<Offer> findById(long offerId);
    List<Offer> findAllBySeller_Id(long sellerId);
}
