package bg.mobilele.service.impl;

import bg.mobilele.model.DTO.BrandDTO;
import bg.mobilele.model.entity.Brand;
import bg.mobilele.model.entity.Model;
import bg.mobilele.repository.BrandRepository;
import bg.mobilele.repository.ModelRepository;
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
    private final ModelMapper mapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelRepository modelRepository, ModelMapper mapper) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
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

    private Brand addNewModel(BrandDTO brandDTO) {
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
        return brand;
    }

    //delete model by id
    @Override
    public void removeModel(long id) {
        modelRepository.deleteById(id);
    }
}


