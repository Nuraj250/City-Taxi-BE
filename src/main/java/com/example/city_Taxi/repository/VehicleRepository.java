package com.example.city_Taxi.repository;

import com.example.city_Taxi.model.Vehicle;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    List<Vehicle> findAllByUserId(Long userID);
}
