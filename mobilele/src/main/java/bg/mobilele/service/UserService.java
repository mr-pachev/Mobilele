package bg.mobilele.service;

import bg.mobilele.model.DTO.UserRegistrationDTO;

public interface UserService {
    void registrationUser(UserRegistrationDTO userRegistrationDTO);
    boolean isExistUser(String username);

    boolean isExistEmail(String email);
}
