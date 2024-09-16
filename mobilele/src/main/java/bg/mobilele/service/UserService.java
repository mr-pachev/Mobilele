package bg.mobilele.service;

import bg.mobilele.model.DTO.UserLoginDTO;
import bg.mobilele.model.DTO.UserRegistrationDTO;

public interface UserService {
    void registrationUser(UserRegistrationDTO userRegistrationDTO);
    boolean isLogin(UserLoginDTO userLoginDTO);
}
