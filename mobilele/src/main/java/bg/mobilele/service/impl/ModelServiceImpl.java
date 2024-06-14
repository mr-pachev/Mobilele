package bg.mobilele.service.impl;

import bg.mobilele.model.DTO.AddOfferDTO;
import bg.mobilele.model.entity.Brand;
import bg.mobilele.model.entity.Model;
import bg.mobilele.repository.BrandRepository;
import bg.mobilele.repository.ModelRepository;
import bg.mobilele.service.ModelService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModelServiceImpl implements ModelService {
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    public ModelServiceImpl(BrandRepository brandRepository, ModelRepository modelRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public List<Model> allCurrentModels(AddOfferDTO addOfferDTO) {
        Brand currentBrand = brandRepository.findByName(addOfferDTO.getBrand());

        return modelRepository.findAllByBrand(currentBrand);
    }
}
