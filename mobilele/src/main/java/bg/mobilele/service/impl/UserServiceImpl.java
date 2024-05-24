package bg.mobilele.service.impl;

import bg.mobilele.model.DTO.UserLoginDTO;
import bg.mobilele.model.DTO.UserRegistrationDTO;
import bg.mobilele.model.entity.User;
import bg.mobilele.repository.UserRepository;
import bg.mobilele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public void registrationUser(UserRegistrationDTO userRegistrationDTO) {
        Optional<User> newUser = userRepository.findByUsername(userRegistrationDTO.getUsername());
        if(newUser.isPresent()){
            System.out.println("--- This user already exist! ---");
            return;
        }


        User user = mapper.map(userRegistrationDTO, User.class);

        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
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
