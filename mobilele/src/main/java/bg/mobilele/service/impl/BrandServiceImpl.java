package bg.mobilele.service.impl;

import bg.mobilele.model.DTO.BrandDTO;
import bg.mobilele.model.entity.Brand;
import bg.mobilele.model.entity.Model;
import bg.mobilele.repository.BrandRepository;
import bg.mobilele.repository.ModelRepository;
import bg.mobilele.repository.OfferRepository;
import bg.mobilele.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service

public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final OfferRepository offerRepository;
    private final ModelMapper mapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelRepository modelRepository, OfferRepository offerRepository, ModelMapper mapper) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.offerRepository = offerRepository;
        this.mapper = mapper;
    }

    //get all brands
    @Override
    public List<Brand> allBrands() {
        return brandRepository.findAll();
    }

    //get brand by name
    @Override
    public Brand findByBrandName(String brandName) {

        return brandRepository.findByName(brandName).orElseThrow();
    }

    //get models by brand
    @Override
    public List<String> modelsByBrand(String branName) {
        List<String> models = findByBrandName(branName).getModels().stream()
                .map(Model::getModelName)
                .toList();

        return models;
    }

    //get BrandDTO by model id
    @Override
    public BrandDTO findByModelId(long id) {
        Model currentModel = modelRepository.findById(id).orElseThrow();

        BrandDTO brandDTO = mapper.map(currentModel, BrandDTO.class);
        brandDTO.setName(currentModel.getBrand().getName());

        return brandDTO;
    }

    //add new brand or model
    @Override
    public void addBrand(BrandDTO brandDTO) {
        addNewBrand(brandDTO);
        addNewModel(brandDTO);
    }

    private void addNewBrand(BrandDTO brandDTO) {
        Brand brand;

        if (brandRepository.findByName(brandDTO.getName()).isPresent()) {
            brand = brandRepository.findByName(brandDTO.getName()).orElseThrow();

        } else {
            brand = mapper.map(brandDTO, Brand.class);
            brand.setCreated(LocalDateTime.now());
        }
        brand.setModifier(LocalDateTime.now());

        brandRepository.save(brand);
    }

    private void addNewModel(BrandDTO brandDTO) {
        Brand brand = brandRepository.findByName(brandDTO.getName()).orElseThrow();

        Model model = mapper.map(brandDTO, Model.class);
        model.setBrand(brand);
        model.setCreated(LocalDateTime.now());
        model.setModified(LocalDateTime.now());

        modelRepository.save(model);

        List<Model> currentModels = new ArrayList<>();
        if (brand.getModels() != null) {
            currentModels = brand.getModels();
        }
        currentModels.add(model);
        brand.setModels(currentModels);
    }

    //delete model by id
    @Override
    public void removeModel(long id) {
        offerRepository.deleteById(id);
        modelRepository.deleteById(id);
    }

    //delete brand by id
    @Override
    public void removeBrand(long id) {
        Brand brand = brandRepository.findById(id).orElseThrow();

        removeModels(brand.getModels());

        brandRepository.deleteById(id);
    }

    void removeModels(List<Model> modelList) {
        for (Model model : modelList) {
            removeModel(model.getId());
        }
    }
}


