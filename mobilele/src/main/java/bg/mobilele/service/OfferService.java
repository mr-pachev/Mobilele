package bg.mobilele.service;

import bg.mobilele.model.DTO.AddOfferDTO;
import bg.mobilele.model.entity.Offer;

import java.util.List;

public interface OfferService {
    long addOffer(AddOfferDTO addOfferDTO);
    //get offer by id
    AddOfferDTO getAddOfferDTOById(long id);

    List<Offer> allOfferInCurrentSeller(long sellerId);
    void deleteOffer(long offerId);
}
