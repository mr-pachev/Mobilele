package bg.mobilele.web;

import bg.mobilele.data.DTO.UserRegistrationDTO;
import bg.mobilele.data.entity.User;
import bg.mobilele.repository.UserRepository;
import bg.mobilele.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {
   private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users/register")
    public String showRegistrationForm() {
        return ("auth-register");
    }

    @PostMapping("users/register")
    public String registerUser(UserRegistrationDTO userRegistrationDTO) {

        userService.registrationUser(userRegistrationDTO);

        return "redirect:/";
    }

}
