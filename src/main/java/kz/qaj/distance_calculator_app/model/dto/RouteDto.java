package kz.qaj.distance_calculator_app.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class RouteDto {

    private Long source;
    private Long target;
    private BigDecimal distance;
    private Boolean isDetour;
}
