package bg.mobilele.service.impl;

import bg.mobilele.model.DTO.UserRegistrationDTO;
import bg.mobilele.model.entity.User;
import bg.mobilele.model.entity.UserRole;
import bg.mobilele.repository.UserRepository;
import bg.mobilele.repository.UserRoleRepository;
import bg.mobilele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
//        currentUser.setLogin(true);
//        currentUser.setUsername(user.getUsername());
//        currentUser.setFirstName(user.getFirstName());
//        currentUser.setLastName(user.getLastName());

        userRepository.save(user);
    }
}
