package bg.mobilele.web;

import bg.mobilele.model.DTO.UserDTO;
import bg.mobilele.model.DTO.UserRegistrationDTO;
import bg.mobilele.model.enums.Role;
import bg.mobilele.repository.UserRepository;
import bg.mobilele.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @ModelAttribute("userDTO")
    public UserDTO userDTO() {
        return new UserDTO();
    }

    //add new user
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

        userRegistrationDTO.setUserIsExist(userService.isExistUser(userRegistrationDTO.getUsername()));

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

    //view all users
    @GetMapping("/users")
    public String getAllUsers(Model model){
        List<UserDTO> userDTOS = userService.getAllUsers();

        model.addAttribute("userDTOS", userDTOS);

        return "users";
    }

    //edit current user
    @PostMapping("/edit-user/{id}")
    public String referenceToEditUserForm(@PathVariable("id") Long id){

        return "redirect:/edit-user/" + id;
    }

    @GetMapping("/edit-user/{id}")
    public String fillEditUserForm(@PathVariable("id") Long id, Model model) {
        UserDTO userDTO = userService.getUserDetails(id);

        model.addAttribute(userDTO);
        model.addAttribute("roles", Role.values());

        return "edit-user";
    }

    @PostMapping("/edit-user")
    public String editUser(@RequestParam("id") Long userId,
                           @Valid UserDTO userDTO,
                           BindingResult bindingResult,
                           RedirectAttributes rAtt,
                           Model model){

        userDTO.setId(userId);

        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("userDTO", userDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.userDTO", bindingResult);
            model.addAttribute("roles", Role.values());

            return "edit-user";
        }

        String currentUsernameForEdit = userRepository.findById(userId)
                .orElseThrow()
                .getUsername();

        boolean isChangedUsername = !currentUsernameForEdit.equals(userDTO.getUsername());

        if(userService.isExistUser(userDTO.getUsername()) && isChangedUsername){
            rAtt.addFlashAttribute("userDTO", userDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.userDTO", bindingResult);
            model.addAttribute("roles", Role.values());

            model.addAttribute("isExistUsername", true);

            return "edit-user";
        }

        String currentUsernameEmailForEdit = userRepository.findById(userId)
                .orElseThrow()
                .getEmail();

        boolean isChangedEmail = !currentUsernameEmailForEdit.equals(userDTO.getEmail());

        if(userService.isExistEmail(userDTO.getEmail()) && isChangedEmail){
            rAtt.addFlashAttribute("userDTO", userDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.userDTO", bindingResult);
            model.addAttribute("roles", Role.values());

            model.addAttribute("isExistEmail", true);

            return "edit-user";
        }

        userService.editUser(userDTO);

        return "redirect:/users";
    }


}
