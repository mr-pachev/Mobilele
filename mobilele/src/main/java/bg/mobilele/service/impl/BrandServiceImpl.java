package bg.mobilele.service.impl;

import bg.mobilele.model.DTO.AddBrandDTO;
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

    //add new brand
    @Override
    public void addBrand(AddBrandDTO addBrandDTO) {
        List<Model> currentModels = new ArrayList<>();
        Brand brand;

        if (brandRepository.findByName(addBrandDTO.getName()).isPresent()) {
            brand = brandRepository.findByName(addBrandDTO.getName()).orElseThrow();

            currentModels = brand.getModels();
        } else {
            brand = mapper.map(addBrandDTO, Brand.class);
            brand.setCreated(LocalDateTime.now());
        }
        brand.setModifier(LocalDateTime.now());

        Model model = mapper.map(addBrandDTO, Model.class);
        model.setBrand(brand);
        model.setCreated(LocalDateTime.now());
        model.setModified(LocalDateTime.now());

        currentModels.add(model);
        brand.setModels(currentModels);

        brandRepository.save(brand);
        modelRepository.save(model);
    }
}
