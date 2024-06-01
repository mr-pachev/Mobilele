package bg.mobilele.web;

import bg.mobilele.model.entity.Brand;
import bg.mobilele.model.enums.Engine;
import bg.mobilele.model.enums.Transmission;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class OfferController {
    @GetMapping("offers/all")
    public String allOffers(){
        return ("offers");
    }

    @GetMapping("offers/add")
    public String addOffer(Model model){
        model.addAttribute("engineType", Engine.values());
        model.addAttribute("transmissionType", Transmission.values());

        return ("offer-add");
    }

}
