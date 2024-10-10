package bg.mobilele.service;

import bg.mobilele.model.DTO.AddOfferDTO;
import bg.mobilele.model.entity.Brand;
import bg.mobilele.model.entity.Model;

import java.util.List;
import java.util.Map;

public interface ModelService {
    public List<Model> allModel();

    boolean isExistModel(String modelName);
}
