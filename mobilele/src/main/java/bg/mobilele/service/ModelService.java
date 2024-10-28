package bg.mobilele.service;

import bg.mobilele.model.entity.Model;

import java.util.List;

public interface ModelService {
    //get all models
    public List<Model> allModel();

    //check is exist model by name
    boolean isExistModel(String modelName);
}
