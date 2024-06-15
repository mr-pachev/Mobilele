package bg.mobilele.service.impl;

import bg.mobilele.model.DTO.AddOfferDTO;
import bg.mobilele.model.entity.Model;
import bg.mobilele.model.entity.Offer;
import bg.mobilele.repository.ModelRepository;
import bg.mobilele.repository.OfferRepository;
import bg.mobilele.repository.UserRepository;
import bg.mobilele.service.OfferService;
import bg.mobilele.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OfferServiceImpl implements OfferService {
    private final ModelMapper mapper;
    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;

    public OfferServiceImpl(ModelMapper mapper, OfferRepository offerRepository, ModelRepository modelRepository, CurrentUser currentUser, UserRepository userRepository) {
        this.mapper = mapper;
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
    }

    @Override
    public void addOffer(AddOfferDTO addOfferDTO) {
        Offer offer = mapper.map(addOfferDTO, Offer.class);
        Model model = modelRepository.findModelByModelName(addOfferDTO.getModel());

        offer.setModel(model);
        offer.setCreated(LocalDateTime.now());
        offer.setModified(LocalDateTime.now());
        offer.setSeller(userRepository.findByUsername(currentUser.getUsername()).orElse(null));

        offerRepository.save(offer);
    }

    @Override
    public AddOfferDTO offerDetails(long id) {
        Offer offer = offerRepository.findById(id);
        AddOfferDTO addOfferDTO = mapper.map(offer, AddOfferDTO.class);

        System.out.println();

        return addOfferDTO;
    }

}
