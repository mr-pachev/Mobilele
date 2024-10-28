package bg.mobilele.service.impl;

import bg.mobilele.model.entity.Model;
import bg.mobilele.repository.ModelRepository;
import bg.mobilele.service.ModelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    //get all models
    @Override
    public List<Model> allModel() {
        return modelRepository.findAll();
    }

    //check is exist model by model name
    @Override
    public boolean isExistModel(String modelName) {
        return modelRepository.findModelByModelName(modelName).isPresent();
    }
}
