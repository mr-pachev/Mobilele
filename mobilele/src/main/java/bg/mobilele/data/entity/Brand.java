package bg.mobilele.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
@Entity
@Table(name = "brands")
public class Brand extends BaseEntity{
    private String name;
    private LocalDateTime created;
    private LocalDateTime modifier;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModifier() {
        return modifier;
    }

    public void setModifier(LocalDateTime modifier) {
        this.modifier = modifier;
    }
}
