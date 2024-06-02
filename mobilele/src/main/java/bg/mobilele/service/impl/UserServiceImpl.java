package bg.mobilele.service.impl;

import bg.mobilele.model.DTO.UserLoginDTO;
import bg.mobilele.model.DTO.UserRegistrationDTO;
import bg.mobilele.model.entity.User;
import bg.mobilele.model.entity.UserRole;
import bg.mobilele.repository.UserRepository;
import bg.mobilele.repository.UserRoleRepository;
import bg.mobilele.service.UserService;
import bg.mobilele.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public void registrationUser(UserRegistrationDTO userRegistrationDTO) {
        User user = mapper.map(userRegistrationDTO, User.class);
        UserRole userRole = mapper.map(userRegistrationDTO, UserRole.class);

        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setActive(true);
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());

        Set<User> currentUsers = new HashSet<>();
        currentUsers.add(user);
        userRole.setUsers(currentUsers);

        user.setUserRole(userRole);

        userRoleRepository.save(userRole);
        userRepository.save(user);
    }

    @Override
    public boolean isLogin(UserLoginDTO userLoginDTO) {
        Optional<User> loginUser = userRepository.findByUsername(userLoginDTO.getUsername());
        CurrentUser currentUser = new CurrentUser();

        if(loginUser.isPresent() && passwordEncoder.matches(userLoginDTO.getPassword(), loginUser.get().getPassword())){
            currentUser.setLogin(true);
            currentUser.setUsername(currentUser.getUsername());
            currentUser.setFirstName(currentUser.getFirstName());
            currentUser.setLastName(loginUser.get().getLastName());

            return true;
        }
        userLoginDTO.setIsLoginUser(true);
        return false;
    }
}
