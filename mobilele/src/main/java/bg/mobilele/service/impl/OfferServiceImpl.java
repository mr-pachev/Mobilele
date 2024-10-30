package bg.mobilele.service.impl;

import bg.mobilele.model.DTO.AddOfferDTO;
import bg.mobilele.model.DTO.OfferDTO;
import bg.mobilele.model.entity.Brand;
import bg.mobilele.model.entity.Model;
import bg.mobilele.model.entity.Offer;
import bg.mobilele.model.enums.Engine;
import bg.mobilele.model.enums.Transmission;
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

    //get all offers by current user id
    @Override
    public List<Offer> allOfferInCurrentSeller(long sellerId) {

        return offerRepository.findAllBySeller_Id(sellerId);
    }

    //get offerDTO by id
    @Override
    public OfferDTO getOfferDTOById(long id) {
        Offer offer = offerRepository.findById(id).orElseThrow();

        OfferDTO offerDTO = mapper.map(offer, OfferDTO.class);
        offerDTO.setBrand(offer.getBrand().getName());

        return offerDTO;
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

    //edit offer
    @Override
    public void editOffer(OfferDTO offerDTO) {
        Offer offer = offerDTOmapToOffer(offerDTO);

        offerRepository.save(offer);
    }

    private Offer offerDTOmapToOffer(OfferDTO offerDTO) {
        Offer offer = offerRepository.findById(offerDTO.getOfferId()).orElseThrow();

        offer.setDescription(offerDTO.getDescription());
        offer.setEngine(Engine.valueOf(offerDTO.getEngine()));
        offer.setImageUrl(offerDTO.getImageUrl());
        offer.setMileage(offerDTO.getMileage());
        offer.setPrice(offerDTO.getPrice());
        offer.setTransmission(Transmission.valueOf(offerDTO.getTransmission()));
        offer.setYear(offerDTO.getYear());

        Model model = modelRepository.findModelByModelName(offerDTO.getModel()).orElseThrow();
        Brand brand = brandRepository.findByName(offerDTO.getBrand()).orElseThrow();

        offer.setBrand(brand);
        offer.setModel(model);
        offer.setModified(LocalDate.now());

        return offer;
    }

    //delete offer by id
    @Override
    public void deleteOffer(long offerId) {
        offerRepository.deleteById(offerId);
    }

    //delete offer by brand id
    @Override
    public void deleteOfferByBrand(long brandId) {
        List<Offer> offersByBrand = offerRepository.findAllByBrandId(brandId);

        if (!offersByBrand.isEmpty()) {
            for (Offer offer : offersByBrand) {
                offerRepository.deleteById(offer.getId());
            }
        }
    }

    //delete offer by model id
    @Override
    public void deleteOfferByModel(long modelId) {
        List<Offer> offersByModel = offerRepository.findAllByModelId(modelId);

        if (!offersByModel.isEmpty()) {
            for (Offer offer : offersByModel) {
                offerRepository.deleteById(offer.getId());
            }
        }
    }
}
