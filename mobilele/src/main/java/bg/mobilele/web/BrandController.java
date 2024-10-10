package bg.mobilele.web;

import bg.mobilele.model.DTO.BrandDTO;
import bg.mobilele.model.enums.Category;
import bg.mobilele.service.BrandService;
import bg.mobilele.service.ModelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class BrandController {

    private final BrandService brandService;
    private final ModelService modelService;

    public BrandController(BrandService brandService, ModelService modelService) {
        this.brandService = brandService;
        this.modelService = modelService;
    }

    @ModelAttribute("brandDTO")
    public BrandDTO brandDTO() {
        return new BrandDTO();
    }

    //view all brands
    @GetMapping("brands/all")
    public String viewBrands(Model model) {
        model.addAttribute("brands", brandService.allBrands());
        model.addAttribute("models", modelService.allModel());

        return "brands";
    }

    //add new brand
    @GetMapping("/add-brand")
    public String viewAddBrand(Model model) {

        model.addAttribute("categoryType", Category.values());

        return "add-brand";
    }



}
