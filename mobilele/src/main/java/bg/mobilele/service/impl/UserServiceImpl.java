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
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, ModelMapper mapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.currentUser = currentUser;
    }

    @Override
    public void registrationUser(UserRegistrationDTO userRegistrationDTO) {
        //setting new user fields
        User user = mapper.map(userRegistrationDTO, User.class);

        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setActive(true);
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());

        //setting UserRole field
        UserRole userRole;

        if(userRegistrationDTO.getRole().equals("User")){
            userRole = userRoleRepository.findAllById(1);
        }else {
            userRole = userRoleRepository.findAllById(2);
        }

        Set<User> users = userRole.getUsers();
        users.add(user);

        user.setUserRole(userRole);

        //setting sessions currentUser
        currentUser.setLogin(true);
        currentUser.setUsername(user.getUsername());
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());

        userRepository.save(user);
    }

    @Override
    public boolean isLogin(UserLoginDTO userLoginDTO) {
        Optional<User> loginUser = userRepository.findByUsername(userLoginDTO.getUsername());

        if(loginUser.isPresent() && passwordEncoder.matches(userLoginDTO.getPassword(), loginUser.get().getPassword())){
            //setting sessions currentUser
            currentUser.setLogin(true);
            currentUser.setUsername(loginUser.get().getUsername());
            currentUser.setFirstName(loginUser.get().getFirstName());
            currentUser.setLastName(loginUser.get().getLastName());
            currentUser.setCurrentUserId(loginUser.get().getId());

            return true;
        }
        userLoginDTO.setLoginUser(true);
        return false;
    }
}
