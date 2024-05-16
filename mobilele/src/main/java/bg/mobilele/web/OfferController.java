package bg.mobilele.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class OfferController {
    @GetMapping("offers/all")
    public ModelAndView allOffers(){
        return new ModelAndView("offers");
    }

    @GetMapping("offers/add")
    public ModelAndView addOffer(){
        return new ModelAndView("offer-add");
    }

}
