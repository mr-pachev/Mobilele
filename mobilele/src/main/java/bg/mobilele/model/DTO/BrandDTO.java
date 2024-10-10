package bg.mobilele.model.DTO;

import jakarta.validation.constraints.*;

public class BrandDTO {
    @NotBlank
    @Size(min = 3, max = 15)
    private String name;

    @NotBlank
    private String category;

    @NotBlank
    @Size(min = 3, max = 15)
    private String models;

    @NotNull
    @Min(1920)
    @Max(2024)
    private Integer startYear;

    @NotNull
    @Min(1920)
    @Max(2024)
    private Integer endYear;

    @NotBlank
    private String created;
    @NotBlank
    private String modifier;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
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
}
