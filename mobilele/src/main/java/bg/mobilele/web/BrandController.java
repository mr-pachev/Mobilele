package bg.mobilele.web;

import bg.mobilele.model.DTO.BrandDTO;
import bg.mobilele.model.enums.Category;
import bg.mobilele.service.BrandService;
import bg.mobilele.service.ModelService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/add-brand")
    public String creatBrand(
            @Valid BrandDTO brandDTO,
            BindingResult bindingResult,
            RedirectAttributes rAtt) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("brandDTO", brandDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.brandDTO", bindingResult);

            return "redirect:/add-brand";
        }

        if (modelService.isExistModel(brandDTO.getModelName())) {
            rAtt.addFlashAttribute("brandDTO", brandDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.brandDTO", bindingResult);
            rAtt.addFlashAttribute("isExist", true);

            return "redirect:/add-brand";
        }

        brandService.brandDTO(brandDTO);

        return "redirect:/brands/all";
    }

    //edit current brand
    @PostMapping("/add-brand/{id}")
    public String referenceToEdithBrandForm(@PathVariable("id") Long id){

        return "redirect:/add-brand/" + id;
    }

    @GetMapping("/add-brand/{id}")
    public String fillAddBrandForm(@PathVariable("id") Long id, Model model) {
        BrandDTO brandDTO = brandService.findByModelId(id);

        model.addAttribute("categoryType", Category.values());
        model.addAttribute(brandDTO);

        return "add-brand";
    }
//
//    @PostMapping("/department-details")
//    public String edithDepartment(@RequestParam("id") Long id,
//                                  @Valid DepartmentDTO departmentDTO,
//                                  BindingResult bindingResult,
//                                  RedirectAttributes rAtt,
//                                  Model model){
//
//        departmentDTO.setId(id);
//
//        if(bindingResult.hasErrors()){
//            rAtt.addFlashAttribute("departmentDTO", departmentDTO);
//            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.departmentDTO", bindingResult);
//
//            model.addAttribute("allEmployees", employeeService.getAllEmployeesNames());
//            return "department-details";
//        }
//
//        departmentService.editDepartment(departmentDTO);
//        return "redirect:/departments";
//    }
}
