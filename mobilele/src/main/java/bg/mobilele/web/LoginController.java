package bg.mobilele.web;

import bg.mobilele.model.DTO.UserLoginDTO;
import bg.mobilele.model.entity.User;
import bg.mobilele.repository.UserRepository;
import bg.mobilele.service.UserService;
import bg.mobilele.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class LoginController {

    private final UserService userService;
    private final CurrentUser currentUser;

    public LoginController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("users/login")
    public String showLoginForm() {
        return "auth-login";
    }

    @PostMapping("users/login")
    public String login(UserLoginDTO userLoginDTO) {
       Optional<User> loginUser = userService.isLogin(userLoginDTO);

        if(loginUser.isPresent()){
            currentUser.setLogin(true);

            return "redirect:register";
        }

        return "redirect:/";
    }
}
