package bg.mobilele.model.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {
    @NotNull
    @Size(min=4, max=10)
    private String username;
    @Size(min=4, max=10)
    private String password;
    private boolean isLoginUser;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoginUser() {
        return isLoginUser;
    }

    public void setLoginUser(boolean loginUser) {
        isLoginUser = loginUser;
    }
}
