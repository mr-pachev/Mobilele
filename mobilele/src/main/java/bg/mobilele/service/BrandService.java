package bg.mobilele.service;

import bg.mobilele.model.DTO.BrandDTO;
import bg.mobilele.model.entity.Brand;

import java.util.List;

public interface BrandService {
    //get all brands
    List<Brand> allBrands();

    //add new brand or model
    void addBrand(BrandDTO brandDTO);

    //get models by brand
    List<String> modelsByBrand (String branName);

    //get brand by name
    Brand findByBrandName(String brandName);

    //get brandDTO
    BrandDTO findByModelId(long id);

    //delete model by id
    void removeModel(long id);

    //delete brand by id
    void removeBrand(long id);
}
