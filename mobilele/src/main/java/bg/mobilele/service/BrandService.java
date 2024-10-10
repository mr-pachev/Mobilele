package bg.mobilele.service;

import bg.mobilele.model.DTO.AddBrandDTO;
import bg.mobilele.model.entity.Brand;
import bg.mobilele.model.entity.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BrandService {
    public List<Brand> allBrands();
    public Brand findByBrandName(String brandName);
    void addBrand(AddBrandDTO addBrandDTO);
}
