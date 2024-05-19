package bg.mobilele.web;

import bg.mobilele.data.DTO.UserRegistrationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class RegisterController {
    @GetMapping("users/register")
    public ModelAndView register(UserRegistrationDTO userRegistrationDTO) {
        return new ModelAndView("auth-register");
    }
}
