package bg.mobilele.service.impl;

import bg.mobilele.model.DTO.AddBrandDTO;
import bg.mobilele.model.entity.Brand;
import bg.mobilele.repository.BrandRepository;
import bg.mobilele.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper mapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper mapper) {
        this.brandRepository = brandRepository;
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
        Brand brand = mapper.map(addBrandDTO, Brand.class);


        brand.setCreated(LocalDateTime.now());
        brand.setModifier(LocalDateTime.now());

        System.out.println();
    }
}
