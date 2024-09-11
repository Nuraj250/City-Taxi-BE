package com.example.city_Taxi.repository;

import com.example.city_Taxi.model.Trip;
import com.example.city_Taxi.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip,Long> {
    List<Trip> findByPassengerId(Long userId);
    List<Trip> findByDriverId(Long driverId);
}
