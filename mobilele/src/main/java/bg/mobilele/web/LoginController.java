package bg.mobilele.web;

import bg.mobilele.model.DTO.UserLoginDTO;
import bg.mobilele.service.UserService;
import bg.mobilele.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    @ModelAttribute("userLoginDTO")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }

    private final UserService userService;
    private final CurrentUser currentUser;

    public LoginController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("login")
    public String showLoginForm() {

        return "login";
    }

    @GetMapping("login-error")
    public String viewLoginError(Model model) {
        model.addAttribute("showErrorMessage", true);
        return "login";
    }
}
