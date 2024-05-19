package bg.mobilele.data.DTO;

import java.util.UUID;

public class ModelDTO {
    private String modelName;
    private UUID id;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
