package bg.mobilele.service.impl;

import bg.mobilele.model.entity.Brand;
import bg.mobilele.repository.BrandRepository;
import bg.mobilele.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> allBrand() {
        return brandRepository.findAll();
    }
}
