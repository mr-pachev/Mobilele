package bg.mobilele.service.impl;

import bg.mobilele.model.DTO.AddOfferDTO;
import bg.mobilele.model.entity.Offer;
import bg.mobilele.repository.OfferRepository;
import bg.mobilele.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {
    private final ModelMapper mapper;
    private final OfferRepository offerRepository;

    public OfferServiceImpl(ModelMapper mapper, OfferRepository offerRepository) {
        this.mapper = mapper;
        this.offerRepository = offerRepository;
    }

    @Override
    public void addOffer(AddOfferDTO addOfferDTO) {
        Offer offer = mapper.map(addOfferDTO, Offer.class);

        offerRepository.save(offer);
    }
}
