package bg.mobilele.web;

import bg.mobilele.data.DTO.UserRegistrationDTO;
import bg.mobilele.data.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LoginController {
    @GetMapping("users/login")
    public ModelAndView login() {
        return new ModelAndView("auth-login");
    }

    @PostMapping("register/")
    public String register(UserRegistrationDTO userRegistrationDTO){


        return "redirect:/auth-login";
    }
}
