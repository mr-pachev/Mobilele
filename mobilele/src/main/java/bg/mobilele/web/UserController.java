package bg.mobilele.web;

import bg.mobilele.model.DTO.UserRegistrationDTO;
import bg.mobilele.repository.UserRepository;
import bg.mobilele.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("registration")
    public String showRegistrationForm(Model model) {

        if (!model.containsAttribute("userRegistrationDTO")) {
            model.addAttribute("userRegistrationDTO", new UserRegistrationDTO());
        }

        return "registration";
    }

    @PostMapping("registration")
    public String registerUser(@Valid UserRegistrationDTO userRegistrationDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        //checking and setting username if it exists
        userRegistrationDTO.setUserIsExist(userService.isExistUser(userRegistrationDTO.getUsername()));
        //checking and setting email if it exists
        userRegistrationDTO.setEmailIsExist(userService.isExistEmail(userRegistrationDTO.getEmail()));

        if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())){
            userRegistrationDTO.setUnConfPass(true);
        }

        if (bindingResult.hasErrors()
                || userRegistrationDTO.isUserIsExist()
                || userRegistrationDTO.isEmailIsExist()
                || userRegistrationDTO.isUnConfPass()) {
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);
            return "redirect:/registration";
        }
        userService.registrationUser(userRegistrationDTO);

        return "redirect:/";
    }

}
