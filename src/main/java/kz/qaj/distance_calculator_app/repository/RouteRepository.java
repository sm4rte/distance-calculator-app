package kz.qaj.distance_calculator_app.repository;

import kz.qaj.distance_calculator_app.model.City;
import kz.qaj.distance_calculator_app.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    List<Route> findBySource(City source);

    /*@Query("select * from Route r where r.source.id:cityId ")
    List<Route> findOneRouteByCityId(Long cityId);*/

    List<Route> findAllBySourceOrTarget(City source, City target);

    Optional<Route> findBySourceAndTargetAndIsDetour(City source, City target, boolean isDetour);
}
