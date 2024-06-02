package bg.mobilele.model.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {
    @Size(min=4, max=10)
    @NotNull
    private String username;
    @Size(min=4, max=10)
    @NotNull
    private String password;
    private boolean isLoginUser;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoginUser() {
        return isLoginUser;
    }

    public void setIsLoginUser(boolean loginUser) {
        isLoginUser = loginUser;
    }
}
