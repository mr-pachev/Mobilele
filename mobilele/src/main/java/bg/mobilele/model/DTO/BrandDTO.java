package bg.mobilele.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BrandDTO {
    @NotBlank
    @Size(min = 3, max = 15)
    private String name;
    @NotBlank
    private String created;
    @NotBlank
    private String modifier;
    @NotBlank
    @Size(min = 3, max = 15)
    private String models;

    @NotBlank
    private String category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
