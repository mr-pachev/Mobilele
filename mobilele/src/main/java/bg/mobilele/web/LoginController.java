package bg.mobilele.web;

import bg.mobilele.data.DTO.UserLoginDTO;
import bg.mobilele.data.DTO.UserRegistrationDTO;
import bg.mobilele.data.entity.User;
import bg.mobilele.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("users/login")
    public String showLoginForm() {
        return "auth-login";
    }

    @PostMapping("users/login")
    public String login(UserLoginDTO userLoginDTO){
        userLoginDTO.getUsername();

        return "redirect: index";
    }
}
