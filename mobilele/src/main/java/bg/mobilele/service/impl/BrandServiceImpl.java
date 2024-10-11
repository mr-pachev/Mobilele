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

    @Override
    public void addBrand(AddBrandDTO addBrandDTO) {
        Model model = mapper.map(addBrandDTO, Model.class);
        model.setCreated(LocalDateTime.now());
        model.setModified(LocalDateTime.now());

        if(brandRepository.findByName(addBrandDTO.))

        Brand brand = mapper.map(addBrandDTO, Brand.class);

        Set<Model> currentModels = brand.getModels();
        currentModels.add(model);

        brand.setModels(currentModels);
        brand.setCreated(LocalDateTime.now());
        brand.setModifier(LocalDateTime.now());

        System.out.println();
    }
}
