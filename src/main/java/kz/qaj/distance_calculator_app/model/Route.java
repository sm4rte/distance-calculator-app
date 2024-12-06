package kz.qaj.distance_calculator_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tab_routes")
@Getter
@Setter
@ToString
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_city_id", nullable = false)
    private City source;
    @ManyToOne
    @JoinColumn(name = "target_city_id", nullable = false)
    private City target;
    @NotNull
    private BigDecimal distance;
    @NotNull
    private Boolean isDetour;

    private LocalDateTime modifiedDate;
}
