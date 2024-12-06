package kz.qaj.distance_calculator_app.service;

import kz.qaj.distance_calculator_app.model.City;
import kz.qaj.distance_calculator_app.model.Route;
import kz.qaj.distance_calculator_app.model.dto.CityDto;
import kz.qaj.distance_calculator_app.model.dto.RouteDto;
import kz.qaj.distance_calculator_app.model.dto.RouteUpdateDto;
import kz.qaj.distance_calculator_app.repository.CityRepository;
import kz.qaj.distance_calculator_app.repository.RouteRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.stream.Collectors;


@Service
public class RouteService {

    private final CityRepository cityRepository;
    private final RouteRepository routeRepository;

    public RouteService(CityRepository cityRepository, RouteRepository routeRepository) {
        this.cityRepository = cityRepository;
        this.routeRepository = routeRepository;
    }

    public BigDecimal calculateShortestPath(String startCityName, String endCityName) {
        // Fetch start and end cities
        City startCity = cityRepository.findByName(startCityName)
                .orElseThrow(() -> new IllegalArgumentException("City not found: " + startCityName));
        City endCity = cityRepository.findByName(endCityName)
                .orElseThrow(() -> new IllegalArgumentException("City not found: " + endCityName));

        // Initialize variables
        Map<City, BigDecimal> distances = new HashMap<>();
        Map<City, City> previousCities = new HashMap<>();
        PriorityQueue<City> queue = new PriorityQueue<>(Comparator.comparing(distances::get));

        // Initialize distances and add cities to queue
        for (City city : cityRepository.findAll()) {
            distances.put(city, BigDecimal.valueOf(10_000_000)); // Use large but finite distance
        }
        distances.put(startCity, BigDecimal.ZERO);
        queue.add(startCity);

        // Debugging: Log initial state
        System.out.println("Start City: " + startCity.getName());
        System.out.println("End City: " + endCity.getName());

        // Dijkstra's algorithm
        while (!queue.isEmpty()) {
            City current = queue.poll();

            // Stop if we've reached the end city
            if (current.equals(endCity)) {
                break;
            }

            // Debugging: Log current city
            System.out.println("Processing city: " + current.getName());

            // Process neighbors
            List<Route> routes = routeRepository.findBySource(current);
            for (Route route : routes) {
                City neighbor = route.getTarget();
                BigDecimal newDistance = distances.get(current).add(route.getDistance());

                // Debugging: Log route and distance update
                System.out.println("Route from " + current.getName() + " to " + neighbor.getName() +
                        " with distance " + route.getDistance() + ". Total: " + newDistance);

                // Update distance if shorter path found
                if (newDistance.compareTo(distances.get(neighbor)) < 0) {
                    distances.put(neighbor, newDistance);
                    previousCities.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        // Final distance
        BigDecimal totalDistance = distances.get(endCity);
        if (totalDistance.compareTo(BigDecimal.valueOf(10_000_000)) >= 0) {
            throw new IllegalStateException("No route found between " + startCityName + " and " + endCityName);
        }

        // Debugging: Log final result
        System.out.println("Shortest path from " + startCity.getName() + " to " + endCity.getName() +
                " is " + totalDistance);

        return totalDistance;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll().stream().sorted(Comparator.comparing(City::getName)).collect(Collectors.toList());
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll().stream().sorted(Comparator.comparing(Route::getId).reversed()).collect(Collectors.toList());
    }

    public City saveCity(CityDto cityDto) {
        City newCity = new City();
        newCity.setName(cityDto.getName());
        return cityRepository.save(newCity);
    }

    public Route saveRoute(RouteDto routeDto) throws BadRequestException {
        if (Objects.equals(routeDto.getSource(), routeDto.getTarget())) {
            throw new BadRequestException("Dumb Request");
        }
        Route newRoute = new Route();
        newRoute.setSource(new City(routeDto.getSource()));
        newRoute.setTarget(new City(routeDto.getTarget()));
        newRoute.setDistance(routeDto.getDistance());
        newRoute.setIsDetour(routeDto.getIsDetour());
        return routeRepository.save(newRoute);
    }

    public City updateCityById(Long id, CityDto cityDto) throws BadRequestException {
        Optional<City> optionalCity = cityRepository.findById(id);
        if (optionalCity.isPresent()) {
            City city = optionalCity.get();
            if (city.getName().equals(cityDto.getName()))
                throw new BadRequestException("Dumb request: sending same name!");
            city.setName(cityDto.getName());
            return cityRepository.save(city);
        } else throw new BadRequestException("City not found to update");
    }

    public Route updateRouteById(Long id, RouteUpdateDto routeUpdateDto) throws BadRequestException {
        if (Objects.equals(routeUpdateDto.getSource(), routeUpdateDto.getTarget())) {
            throw new BadRequestException("Dumb Request");
        }
        Optional<Route> optionalRoute = routeRepository.findById(id);
        if (optionalRoute.isPresent()) {
            Route route = optionalRoute.get();
            //TODO: to check for dumb request
            route.setSource(new City(routeUpdateDto.getSource()));
            route.setTarget(new City(routeUpdateDto.getTarget()));
            route.setDistance(routeUpdateDto.getDistance());
            route.setIsDetour(routeUpdateDto.getIsDetour());
            route.setModifiedDate(LocalDateTime.now());
            return routeRepository.save(route);
        } else throw new BadRequestException("Route not found to update");
    }

    public void deleteCityById(Long cityId) {
        if (cityRepository.findById(cityId).isPresent()) {
            cityRepository.deleteById(cityId);
        }
    }

    public void deleteRouteById(Long routeId) {
        if (routeRepository.findById(routeId).isPresent()) {
            routeRepository.deleteById(routeId);
        }
    }
}



/*@Service
@RequiredArgsConstructor
@Slf4j
public class RouteService {

    private final CityRepository cityRepository;
    private final RouteRepository routeRepository;

    public Map<String, Object> findShortestPath(String startCityName, String endCityName) {

        City startCity = cityRepository.findByName(startCityName).orElseThrow(() -> new RuntimeException("Start city not found!"));
        City endCity = cityRepository.findByName(endCityName).orElseThrow(() -> new RuntimeException("End city not found!"));


        Map<Long, Integer> distances = new HashMap<>();
        Map<Long, Long> previous = new HashMap<>();
        PriorityQueue<Map.Entry<Long, Integer>> priorityQueue = new PriorityQueue<>(Map.Entry.comparingByValue());

        cityRepository.findAll().forEach(city -> distances.put(city.getId(), Integer.MAX_VALUE));
        distances.put(startCity.getId(), 0);
        priorityQueue.add(new AbstractMap.SimpleEntry<>(startCity.getId(), 0));

        while (!priorityQueue.isEmpty()) {
            Long currentId = priorityQueue.poll().getKey();
            if (currentId.equals(endCity.getId())) break;

            for (Route route : routeRepository.findBySource(new City(currentId))) {
                Long targetId = route.getTarget().getId();
                int newDistance = distances.get(currentId) + route.getDistance().intValue();

                if (newDistance < distances.get(targetId)) {
                    distances.put(targetId, newDistance);
                    previous.put(targetId, currentId);
                    priorityQueue.add(new AbstractMap.SimpleEntry<>(targetId, newDistance));
                }
            }
        }
        LinkedList<String> path = new LinkedList<>();
        for (Long at = endCity.getId(); at != null; at = previous.get(at)) {
            path.addFirst(cityRepository.findById(at).get().getName());
        }

        int totalDistance = distances.get(endCity.getId());
        return Map.of("path", path, "totalDistance", totalDistance);
    }
}*/
