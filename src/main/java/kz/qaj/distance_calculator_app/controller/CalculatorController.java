package kz.qaj.distance_calculator_app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.qaj.distance_calculator_app.model.dto.CityDto;
import kz.qaj.distance_calculator_app.model.dto.RouteDto;
import kz.qaj.distance_calculator_app.model.dto.RouteUpdateDto;
import kz.qaj.distance_calculator_app.service.RouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class CalculatorController {

    private final RouteService routeService;

    @GetMapping("/shortest-path")
    @Operation(summary = "Api for distance calculation between two cities")
    @Tag(name = "Report list API", description = "описание api")
    public ResponseEntity<Map<String, Object>> getShortestPath(
            @RequestParam String startCityName,
            @RequestParam String endCityName) {
        try {
            BigDecimal totalDistance = routeService.calculateShortestPath(startCityName, endCityName);
            Map<String, Object> response = new HashMap<>();
            response.put("startCity", startCityName);
            response.put("endCity", endCityName);
            response.put("totalDistance", totalDistance);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/nodes")
    @Operation(summary = "API для получение список городов")
    public ResponseEntity<?> getNodes() {
        return ResponseEntity.ok(routeService.getAllCities());
    }

    @GetMapping("/edges")
    @Operation(summary = "API для получение список Routes")
    public ResponseEntity<?> getEdges() {
        return ResponseEntity.ok(routeService.getAllRoutes());
    }

    @PostMapping("/nodes/add")
    @Operation(summary = "API для добавление Города")
    public ResponseEntity<?> saveCity(@RequestBody CityDto cityDto) {
        log.info("cityName: {}", cityDto);
        return ResponseEntity.ok(routeService.saveCity(cityDto));
    }

    @PostMapping("/edges/add")
    @Operation(summary = "API для добавление Route")
    public ResponseEntity<?> saveRoute(@RequestBody RouteDto routeDto) throws BadRequestException {
        log.info("routeDto: {}", routeDto);
        return ResponseEntity.ok(routeService.saveRoute(routeDto));
    }

    @PutMapping("/nodes/{id}")
    @Operation(summary = "API для обнавление Города по ID")
    public ResponseEntity<?> updateCityById(@PathVariable Long id, @RequestBody CityDto cityDto) throws BadRequestException {
        log.info("id to update:{} , newCityName: {}", id, cityDto);
        return ResponseEntity.ok(routeService.updateCityById(id, cityDto));
    }

    @PutMapping("/edges/{id}")
    @Operation(summary = "API для обнавление Route по ID")
    public ResponseEntity<?> updateRouteById(@PathVariable Long id, @RequestBody RouteUpdateDto routeUpdateDto) throws BadRequestException {
        log.info("Id to update:{} , routeUpdateDto: {}", id, routeUpdateDto);
        return ResponseEntity.ok(routeService.updateRouteById(id, routeUpdateDto));
    }

    @DeleteMapping("/nodes/{id}")
    @Operation(summary = "API для удаление Города по ID")
    public ResponseEntity<?> deleteCityById(@PathVariable Long id) {
        log.info("cityId to delete:{}", id);
        routeService.deleteCityById(id);
        return ResponseEntity.ok("city with Id deleted successfully!");
    }

    @DeleteMapping("/edges/{id}")
    @Operation(summary = "API для удаление Route по ID")
    public ResponseEntity<?> deleteRouteById(@PathVariable Long id) {
        log.info("routeId to delete:{}", id);
        routeService.deleteRouteById(id);
        return ResponseEntity.ok("route with Id deleted successfully!");
    }

}

/*@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CalculatorController {

    private final PathService pathService;

    @GetMapping("/calculate")
    public ResponseEntity<?> getShortestPath(@RequestParam String startCityName,
                                             @RequestParam String endCityName) {

        Map<String, Object> result = pathService.findShortestPath(startCityName, endCityName);
        return ResponseEntity.ok(result);
    }
}*/
