package bg.mobilele.service;

import bg.mobilele.model.DTO.UserDTO;
import bg.mobilele.model.DTO.UserRegistrationDTO;

import java.util.List;

public interface UserService {
    //get all users
    List<UserDTO> getAllUsers();

    //check is exist user by username
    boolean isExistUser(String username);

    //check is exist email
    boolean isExistEmail(String email);

    //add  new user
    void registrationUser(UserRegistrationDTO userRegistrationDTO);

    //edit user
    void editUser(UserDTO userDTO);

    //get user details by id
    UserDTO getUserDetails(long id);
}
