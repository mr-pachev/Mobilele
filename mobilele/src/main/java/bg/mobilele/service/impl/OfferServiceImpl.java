package bg.mobilele.service.impl;

import bg.mobilele.model.DTO.AddOfferDTO;
import bg.mobilele.model.entity.Brand;
import bg.mobilele.model.entity.Model;
import bg.mobilele.model.entity.Offer;
import bg.mobilele.repository.BrandRepository;
import bg.mobilele.repository.ModelRepository;
import bg.mobilele.repository.OfferRepository;
import bg.mobilele.repository.UserRepository;
import bg.mobilele.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    private final ModelMapper mapper;
    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final UserRepository userRepository;

    public OfferServiceImpl(ModelMapper mapper, OfferRepository offerRepository, ModelRepository modelRepository, BrandRepository brandRepository, UserRepository userRepository) {
        this.mapper = mapper;
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
    }

    //add offer
    @Override
    public long addOffer(AddOfferDTO addOfferDTO, long userId) {
        Offer offer = mapper.map(addOfferDTO, Offer.class);
        Model model = modelRepository.findModelByModelName(addOfferDTO.getModel());
        Brand brand = brandRepository.findByName(addOfferDTO.getBrand());

        offer.setBrand(brand);
        offer.setModel(model);
        offer.setSeller(userRepository.getReferenceById(userId));

        offerRepository.save(offer);
        return offer.getId();
    }

    //get offer by id
    @Override
    public AddOfferDTO getAddOfferDTOById(long id) {
        Offer offer = offerRepository.findById(id);

        AddOfferDTO addOfferDTO = mapper.map(offer, AddOfferDTO.class);
        addOfferDTO.setBrand(offer.getBrand().getName());

        return addOfferDTO;
    }

    //get all offers by current user id
    @Override
    public List<Offer> allOfferInCurrentSeller(long sellerId) {

        return offerRepository.findAllBySeller_Id(sellerId);
    }

    //edit offer
    @Override
    public void editOffer(AddOfferDTO addOfferDTO) {
        Offer offer = offerRepository.findById(addOfferDTO.getOfferId());

        mapper.map(addOfferDTO, offer);

        offerRepository.save(offer);
    }

    //delete offer by id
    @Override
    public void deleteOffer(long offerId) {
        offerRepository.deleteById(offerId);
    }

}
