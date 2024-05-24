package bg.mobilele.model.entity;

import bg.mobilele.model.enums.Role;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity{
    private String role;

    @OneToMany(targetEntity = User.class, mappedBy = "userRole")
    private Set<User> users;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


}
