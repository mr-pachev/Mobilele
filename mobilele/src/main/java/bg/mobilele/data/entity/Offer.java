package bg.mobilele.data.entity;

import bg.mobilele.data.enums.Engine;
import bg.mobilele.data.enums.Transmission;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity{
    @Column(columnDefinition = "text")
    private String description;
    @Enumerated(EnumType.STRING)
    private Engine engine;
    private String imageUrl;
    private Integer mileage;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    private Integer year;
    private LocalDateTime created;
    private LocalDateTime modified;

}
