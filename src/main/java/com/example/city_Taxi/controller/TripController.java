package com.example.city_Taxi.controller;

import com.example.city_Taxi.dto.TripDTO;
import com.example.city_Taxi.service.TripService;
import com.example.city_Taxi.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trip")
public class TripController {

    @Autowired
    private TripService tripService;

    @PostMapping("/book")
    public ResponseMessage bookTrip(@RequestBody TripDTO tripDTO) {
        return tripService.bookTrip(tripDTO);
    }

    @PutMapping("/start/{tripId}")
    public ResponseMessage startTrip(@PathVariable Long tripId, @RequestParam Long driverId) {
        return tripService.startTrip(tripId, driverId);
    }

    @PutMapping("/end/{tripId}")
    public ResponseMessage endTrip(@PathVariable Long tripId) {
        return tripService.completeTrip(tripId);
    }

    @PutMapping("/cancel/{tripId}")
    public ResponseMessage cancelTrip(@PathVariable Long tripId) {
        return tripService.cancelTrip(tripId);
    }

    @GetMapping("/{tripId}")
    public ResponseMessage getTripDetailsById(@PathVariable Long tripId) {
        return tripService.getTripDetailsById(tripId);
    }

    @GetMapping("/user/{userId}")
    public ResponseMessage getAllTrips(@PathVariable Long userId, @RequestParam boolean isDriver) {
        return tripService.getAllTrip(userId, isDriver);
    }
}
