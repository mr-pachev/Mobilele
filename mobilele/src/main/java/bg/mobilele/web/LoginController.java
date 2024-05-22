package bg.mobilele.web;

import bg.mobilele.model.DTO.UserLoginDTO;
import bg.mobilele.repository.UserRepository;
import bg.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users/login")
    public String showLoginForm() {
        return "auth-login";
    }

    @PostMapping("users/login")
    public String login(UserLoginDTO userLoginDTO) {
       boolean isExist = userService.isLogin(userLoginDTO);

        if(isExist){
            return "redirect:details";
        }

        return "redirect:/";
    }
}
