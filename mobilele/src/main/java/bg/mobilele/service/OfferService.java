package bg.mobilele.service;

import bg.mobilele.model.DTO.AddOfferDTO;
import bg.mobilele.model.DTO.OfferDTO;
import bg.mobilele.model.entity.Offer;

import java.util.List;

public interface OfferService {
    //add offer
    long addOffer(AddOfferDTO addOfferDTO, long userId);

    //get offerDTO by id
    OfferDTO getOfferDTOById(long id);

    //get all offers by current user id
    List<Offer> allOfferInCurrentSeller(long sellerId);

    //edit offer
    void editOffer(AddOfferDTO addOfferDTO);

    //delete offer by id
    void deleteOffer(long offerId);
}
