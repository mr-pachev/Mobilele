package bg.mobilele.web;

import bg.mobilele.model.DTO.AddOfferDTO;
import bg.mobilele.model.DTO.UserRegistrationDTO;
import bg.mobilele.model.entity.Brand;
import bg.mobilele.model.enums.Engine;
import bg.mobilele.model.enums.Role;
import bg.mobilele.model.enums.Transmission;
import bg.mobilele.service.BrandService;
import bg.mobilele.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class OfferController {

    private final OfferService offerService;
    private final BrandService brandService;

    public OfferController(OfferService offerService, BrandService brandService) {
        this.offerService = offerService;
        this.brandService = brandService;
    }

    @GetMapping("offers/all")
    public String allOffers(){
        return ("offers");
    }

    @GetMapping("offers/add")
    public String addOffer(Model model){

        if (!model.containsAttribute("addOfferDTO")) {
            model.addAttribute("addOfferDTO", new AddOfferDTO());
        }

        model.addAttribute("engineType", Engine.values());
        model.addAttribute("transmissionType", Transmission.values());
        model.addAttribute("brands", brandService.allBrand());

        return ("offer-add");
    }

    @PostMapping("offers/add")
    public String addOffer(@Valid AddOfferDTO addOfferDTO,
                           BindingResult bindingResult,
                           RedirectAttributes rAtt){

        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("addOfferDTO", addOfferDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", bindingResult);
            return "redirect:/offers/add";
        }

        offerService.addOffer(addOfferDTO);
        return "details";
    }

}
