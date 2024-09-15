package com.example.city_Taxi.repository;

import com.example.city_Taxi.model.Trip;
import com.example.city_Taxi.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip,Long> {
    List<Trip> findByPassengerId(Long userId);
    List<Trip> findByDriverId(Long driverId);
    @Query(value = "SELECT * FROM trip WHERE " +
            "ST_DWithin(start_location, ST_SetSRID(ST_MakePoint(:startLongitude, :startLatitude), 4326), :distance); ",
            nativeQuery = true)
    List<Trip> searchTrip(@Param("startLongitude") double startLongitude,
                          @Param("startLatitude") double startLatitude,
                          @Param("distance") double distance);
}
