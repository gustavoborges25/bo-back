package com.gusgo.bo.repository.registration;

import com.gusgo.bo.entity.registration.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {

    Optional<City> findByIbgeId(int ibgeId);

    @Query("SELECT c " +
           "FROM City c " +
           "WHERE c.state.id = :stateId " +
           "ORDER BY c.name")
    List<City> findCitiesByState(@Param("stateId") UUID stateId);

}
