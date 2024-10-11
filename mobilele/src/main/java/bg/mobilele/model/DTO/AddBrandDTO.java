package bg.mobilele.model.DTO;

import jakarta.validation.constraints.*;

public class AddBrandDTO {

    @NotBlank
    @Size(min = 3, max = 15)
    private String name;

    @NotBlank
    @Size(min = 3, max = 15)
    private String modelName;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
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
}
