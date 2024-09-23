package com.example.city_Taxi.repository;

import com.example.city_Taxi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<User, Long> {

    // Find a specific driver by ID
    @Query("SELECT u FROM User u WHERE u.id = :driverId AND u.userType = 'driver'")
    Optional<User> findDriverById(@Param("driverId") Long driverId);
}
