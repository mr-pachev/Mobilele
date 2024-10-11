package bg.mobilele.service.impl;

import bg.mobilele.model.DTO.AddBrandDTO;
import bg.mobilele.model.entity.Brand;
import bg.mobilele.model.entity.Model;
import bg.mobilele.repository.BrandRepository;
import bg.mobilele.repository.ModelRepository;
import bg.mobilele.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public List<Brand> allBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand findByBrandName(String brandName) {

        return brandRepository.findByName(brandName).orElseThrow();
    }

    @Transactional
    @Override
    public void addBrand(AddBrandDTO addBrandDTO) {
        Model model = mapper.map(addBrandDTO, Model.class);
        model.setCreated(LocalDateTime.now());
        model.setModified(LocalDateTime.now());

        Brand brand = mapper.map(addBrandDTO, Brand.class);
        brand.setCreated(LocalDateTime.now());
        brand.setModifier(LocalDateTime.now());

        Set<Model> currentModels = new LinkedHashSet<>();

        if(brandRepository.findByName(addBrandDTO.getName()).isPresent()){
            Brand currentBrand = brandRepository.findByName(addBrandDTO.getName()).orElseThrow();
            currentModels = currentBrand.getModels();
        }

        currentModels.add(model);
        brand.setModels(currentModels);

        brandRepository.save(brand);
        modelRepository.save(model);
    }
}
