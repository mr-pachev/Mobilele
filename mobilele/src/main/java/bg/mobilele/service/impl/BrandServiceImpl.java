package bg.mobilele.service.impl;

import bg.mobilele.model.entity.Brand;
import bg.mobilele.model.entity.Model;
import bg.mobilele.repository.BrandRepository;
import bg.mobilele.repository.ModelRepository;
import bg.mobilele.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> allBrands() {
        return brandRepository.findAll();
    }
}
