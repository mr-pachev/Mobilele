package bg.mobilele.model.DTO;

import java.util.Set;

public class BrandDTO {
    private String name;

    private Set<ModelDTO> models;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ModelDTO> getModels() {
        return models;
    }

    public void setModels(Set<ModelDTO> models) {
        this.models = models;
    }
}
