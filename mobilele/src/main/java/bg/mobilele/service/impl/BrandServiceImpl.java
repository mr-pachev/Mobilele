package bg.mobilele.service.impl;

import bg.mobilele.model.entity.Brand;
import bg.mobilele.model.entity.Model;
import bg.mobilele.repository.BrandRepository;
import bg.mobilele.repository.ModelRepository;
import bg.mobilele.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    public BrandServiceImpl(BrandRepository brandRepository, ModelRepository modelRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public Map<String, Model> allBrands() {
        Map<String, Model> brandsMap = new HashMap<>();

        List<Brand> brandList = brandRepository.findAll();
        for (Brand brand : brandList) {
            if (!brandsMap.containsKey(brand.getName())) {
                brandsMap.put(brand.getName(), new Model());
            }
        }

        List<Model> modelList =

        return brandsMap;
    }
}
