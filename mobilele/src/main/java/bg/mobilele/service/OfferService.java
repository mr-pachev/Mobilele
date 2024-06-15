package bg.mobilele.service;

import bg.mobilele.model.DTO.AddOfferDTO;

public interface OfferService {
    void addOffer(AddOfferDTO addOfferDTO);
    AddOfferDTO offerDetails(long id);
}
