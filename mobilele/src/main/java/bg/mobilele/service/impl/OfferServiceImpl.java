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
import bg.mobilele.util.CurrentUser;
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
    private final CurrentUser currentUser;
    private final UserRepository userRepository;

    public OfferServiceImpl(ModelMapper mapper, OfferRepository offerRepository, ModelRepository modelRepository, BrandRepository brandRepository, CurrentUser currentUser, UserRepository userRepository) {
        this.mapper = mapper;
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
    }

    @Override
    public void addOffer(AddOfferDTO addOfferDTO) {
        Offer offer = mapper.map(addOfferDTO, Offer.class);
        Model model = modelRepository.findModelByModelName(addOfferDTO.getModel());
        Brand brand = brandRepository.findByName(addOfferDTO.getBrand());

        offer.setBrand(brand);

        offer.setModel(model);
        offer.setSeller(userRepository.findByUsername(currentUser.getUsername()).orElse(null));

        offerRepository.save(offer);
    }

    @Override
    public AddOfferDTO offerDetails(long id) {
        Offer offer = offerRepository.findById(id);
        AddOfferDTO addOfferDTO = mapper.map(offer, AddOfferDTO.class);

        return addOfferDTO;
    }

    @Override
    public List<Offer> allOfferInCurrentSeller(long sellerId) {

        return offerRepository.findAllBySeller_Id(sellerId);
    }

    @Override
    public void deleteOffer(long offerId) {
        offerRepository.deleteById(offerId);
    }

}
