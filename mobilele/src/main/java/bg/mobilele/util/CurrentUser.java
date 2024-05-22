package bg.mobilele.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {
    private String firstName;
    private String lastName;
    private boolean isLogin;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public void logout(){
        setLogin(false);
        setFirstName(null);
        setLastName(null);
    }

    public String fullName(){
        StringBuilder sb = new StringBuilder();
        sb.append(firstName).append(" ").append(lastName);

        return sb.toString();
    }
}
