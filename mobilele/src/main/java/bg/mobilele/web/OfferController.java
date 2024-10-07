package bg.mobilele.web;

import bg.mobilele.model.DTO.AddOfferDTO;
import bg.mobilele.model.entity.Offer;
import bg.mobilele.model.enums.Engine;
import bg.mobilele.model.enums.Transmission;
import bg.mobilele.service.BrandService;
import bg.mobilele.service.ModelService;
import bg.mobilele.service.OfferService;
import bg.mobilele.service.UserHelperService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class OfferController {

    private final OfferService offerService;
    private final BrandService brandService;
    private final ModelService modelService;
    private final UserHelperService userHelperService;

    public OfferController(OfferService offerService, BrandService brandService, ModelService modelService, UserHelperService userHelperService) {
        this.offerService = offerService;
        this.brandService = brandService;
        this.modelService = modelService;
        this.userHelperService = userHelperService;
    }

    //view all offers
    @GetMapping("/offers/all")
    public String viewAllOffer(Model model) {

        List<Offer> offerList = offerService.allOfferInCurrentSeller(userHelperService.getUser().getId());

        model.addAttribute("offers", offerList);

        return "offers";
    }

    //add new offer
    @GetMapping("/offers/add")
    public String viewAddOffer(Model model) {

        if (!model.containsAttribute("addOfferDTO")) {
            model.addAttribute("addOfferDTO", new AddOfferDTO());
        }

        model.addAttribute("engineType", Engine.values());
        model.addAttribute("transmissionType", Transmission.values());
        model.addAttribute("brands", brandService.allBrands());
        model.addAttribute("models", modelService.allModel());

        return ("offer-add");
    }

    @PostMapping("/offers/add")
    public String creatOffer(@Valid AddOfferDTO addOfferDTO,
                             BindingResult bindingResult,
                             RedirectAttributes rAtt) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("addOfferDTO", addOfferDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", bindingResult);
            return "redirect:/offers/add";
        }
        addOfferDTO.setCreated(LocalDateTime.now());
        addOfferDTO.setModified(LocalDateTime.now());

        long offerId = offerService.addOffer(addOfferDTO);

        return "redirect:/details/" + offerId;
    }

    @GetMapping("/details/{id}")
    public String viewOfferDetail(@PathVariable("id") long id, Model model) {
        AddOfferDTO addOfferDTO = offerService.getAddOfferDTOById(id);

        model.addAttribute("addOfferDTO", offerService.getAddOfferDTOById(id));

        return "details";
    }

    //edit current offer
    @PostMapping("/update/{id}")
    public String referenceToEdithOfferForm(@PathVariable("id") Long id) {

        return "redirect:/update/" + id;
    }

    @GetMapping("/update/{id}")
    public String fillOfferDetailsForm(@PathVariable("id") long id, Model model) {
        AddOfferDTO addOfferDTO = offerService.getAddOfferDTOById(id);

        model.addAttribute("addOfferDTO", offerService.getAddOfferDTOById(id));

        return "update";
    }

//    @PostMapping("/update")
//    public String edithOffer(@RequestParam("id") Long id,
//                             @Valid AddOfferDTO addOfferDTO,
//                             BindingResult bindingResult,
//                             RedirectAttributes rAtt,
//                             Model model) {
//
//        addOfferDTO.setOfferId(id);
//
//        if (bindingResult.hasErrors()) {
//            rAtt.addFlashAttribute("addOfferDTO", addOfferDTO);
//            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", bindingResult);
//
//            model.addAttribute("offer", offerService.getAddOfferDTOById(id));
//            return "update";
//        }
//
//        departmentService.editDepartment(departmentDTO);
//        return "redirect:/departments";
//    }

    @DeleteMapping("/details/{id}")
    public String deleteOffer(@PathVariable("id") Long id) {

        offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }

}
