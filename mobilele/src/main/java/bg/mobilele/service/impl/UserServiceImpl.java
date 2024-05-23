package bg.mobilele.service.impl;

import bg.mobilele.model.DTO.UserLoginDTO;
import bg.mobilele.model.DTO.UserRegistrationDTO;
import bg.mobilele.model.entity.User;
import bg.mobilele.model.entity.UserRole;
import bg.mobilele.model.enums.Role;
import bg.mobilele.repository.UserRepository;
import bg.mobilele.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registrationUser(UserRegistrationDTO userRegistrationDTO) {
        Optional<User> newUser = userRepository.findByUsername(userRegistrationDTO.getUsername());
        if(newUser.isPresent()){
            return;
        }

        User user = new User();

        user.setUsername(userRegistrationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setEmail(userRegistrationDTO.getEmail());
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setActive(true);
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());

        userRepository.save(user);
    }

    @Override
    public Optional<User> isLogin(UserLoginDTO userLoginDTO) {
        Optional<User> currentUser = userRepository.findByUsername(userLoginDTO.getUsername());

        if(currentUser.isPresent() && passwordEncoder.matches(userLoginDTO.getPassword(), currentUser.get().getPassword())){
            return currentUser;
        }
        return null;
    }
}
