package bg.mobilele.service;

import bg.mobilele.model.DTO.UserLoginDTO;
import bg.mobilele.model.DTO.UserRegistrationDTO;
import bg.mobilele.model.entity.User;

import java.util.Optional;

public interface UserService {
    void registrationUser(UserRegistrationDTO userRegistrationDTO);
    boolean isLogin(UserLoginDTO userLoginDTO);
}
