package bg.mobilele.service.impl;

import bg.mobilele.model.DTO.AddOfferDTO;
import bg.mobilele.model.DTO.OfferDTO;
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

import java.time.LocalDate;
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
        Model model = modelRepository.findModelByModelName(addOfferDTO.getModel()).orElseThrow();
        Brand brand = brandRepository.findByName(addOfferDTO.getBrand()).orElseThrow();

        Offer offer = mapper.map(addOfferDTO, Offer.class);

        offer.setCreated(LocalDate.now());
        offer.setModified(LocalDate.now());

        offer.setBrand(brand);
        offer.setModel(model);
        offer.setSeller(userRepository.getReferenceById(userId));

        return offerRepository.save(offer).getId();
    }

    //get offer by id
    @Override
    public OfferDTO getOfferDTOById(long id) {
        Offer offer = offerRepository.findById(id).orElseThrow();

        OfferDTO offerDTO = mapper.map(offer, OfferDTO.class);
        offerDTO.setBrand(offer.getBrand().getName());

        return offerDTO;
    }

    //get all offers by current user id
    @Override
    public List<Offer> allOfferInCurrentSeller(long sellerId) {

        return offerRepository.findAllBySeller_Id(sellerId);
    }

    //edit offer
    @Override
    public void editOffer(AddOfferDTO addOfferDTO) {

    }

    //delete offer by id
    @Override
    public void deleteOffer(long offerId) {
        offerRepository.deleteById(offerId);
    }

}
