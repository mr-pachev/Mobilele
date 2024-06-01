package bg.mobilele.model.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegistrationDTO {
    @NotNull
    @Column(unique = true)
    @Size(min = 4, max = 10)
    private String username;
    @NotNull
    @Size(min = 4, max = 10)
    private String password;
    @Email
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    @Size(min = 3, max = 10)
    private String firstName;
    @NotNull
    @Size(min = 3, max = 10)
    private String lastName;
    @NotNull
    private String role;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
