package bg.mobilele.service.impl;

import bg.mobilele.data.DTO.UserRegistrationDTO;
import bg.mobilele.repository.UserRepository;
import bg.mobilele.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registrationUser(UserRegistrationDTO userRegistrationDTO) {

    }
}
