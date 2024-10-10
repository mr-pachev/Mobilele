package bg.mobilele.web;

import bg.mobilele.model.DTO.AddBrandDTO;
import bg.mobilele.model.enums.Category;
import bg.mobilele.service.BrandService;
import bg.mobilele.service.ModelService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class BrandController {

    private final BrandService brandService;
    private final ModelService modelService;

    public BrandController(BrandService brandService, ModelService modelService) {
        this.brandService = brandService;
        this.modelService = modelService;
    }

    @ModelAttribute("addBrandDTO")
    public AddBrandDTO addBrandDTO() {
        return new AddBrandDTO();
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

    @PostMapping("/add-brand")
    public String creatBrand(
            @Valid AddBrandDTO addBrandDTO,
            BindingResult bindingResult,
            RedirectAttributes rAtt) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("addBrandDTO", addBrandDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addBrandDTO", bindingResult);

            return "redirect:/add-brand";
        }

        if (brandService.isExistBrand(addBrandDTO.getName())){
            rAtt.addFlashAttribute("addBrandDTO", addBrandDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addDepartmentDTO", bindingResult);

            return "redirect:/add-brand";
        }

        brandService.addBrand(addBrandDTO);

        return "redirect:/brands";
    }

}
