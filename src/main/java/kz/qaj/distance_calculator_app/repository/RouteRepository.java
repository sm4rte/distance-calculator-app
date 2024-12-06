package kz.qaj.distance_calculator_app.repository;

import kz.qaj.distance_calculator_app.model.City;
import kz.qaj.distance_calculator_app.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    List<Route> findBySource(City source);
}
