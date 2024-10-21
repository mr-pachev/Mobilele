package bg.mobilele.service.impl;

import bg.mobilele.model.DTO.UserDTO;
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
import java.util.ArrayList;
import java.util.List;
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

    //add new user
    @Override
    public void registrationUser(UserRegistrationDTO userRegistrationDTO) {

        User user = mapper.map(userRegistrationDTO, User.class);

        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setActive(true);
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());

        UserRole userRole;

        if (userRepository.count() == 0) {
            userRole = userRoleRepository.findAllById(2);
        } else {
            userRole = userRoleRepository.findAllById(1);
        }

        Set<User> users = userRole.getUsers();
        users.add(user);

        user.setUserRole(userRole);

        userRepository.save(user);
    }

    //check is exist user by username
    @Override
    public boolean isExistUser(String username) {

        return userRepository.findByUsername(username).isPresent();
    }

    //check is exist email
    @Override
    public boolean isExistEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    //get user details by id
    @Override
    public UserDTO getUserDetails(long id) {
        User currentUser = userRepository.findById(id).orElseThrow();

        UserDTO userDTO = mapper.map(currentUser, UserDTO.class);
        userDTO.setUserRole(currentUser.getUserRole().getRole().name());

        return userDTO;
    }

    //get all users
    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();

        for (User user : userRepository.findAll()) {
            users.add(mapToUserDTO(user));
        }
        return users;
    }

    @Override
    public void editUser(UserDTO userDTO) {

    }

    private UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        userDTO.setUserRole(user.getUserRole().getRole().name());
        return userDTO;
    }
}
