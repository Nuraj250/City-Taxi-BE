package com.example.city_Taxi.controller;

import com.example.city_Taxi.dto.DriverDTO;
import com.example.city_Taxi.service.DriverService;
import com.example.city_Taxi.util.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/drivers")
@Slf4j
@RestController
public class DriverController {
    @Autowired
    private DriverService driverService;

    // Get driver by ID
    @GetMapping("/{driverId}")
    public ResponseEntity<?> getDriverById(@PathVariable Long driverId) {
        ResponseMessage responseMessage = driverService.getDriverById(driverId);
        return new ResponseEntity<>(responseMessage, HttpStatus.valueOf(responseMessage.getCode()));
    }

    // Update driver's state (available or busy)
    @PutMapping("/{driverId}/state")
    public ResponseEntity<?> updateDriverState(@PathVariable Long driverId, @RequestParam String state) {
        ResponseMessage responseMessage = driverService.updateDriverState(driverId, state);
        return new ResponseEntity<>(responseMessage, HttpStatus.valueOf(responseMessage.getCode()));
    }

    // Add rating and feedback for the driver by customer
    @PostMapping("/{driverId}/rate")
    public ResponseEntity<?> addRatingAndFeedback(
            @PathVariable Long driverId,
            @RequestBody DriverDTO driverDTO) {

        ResponseMessage responseMessage = driverService.addRatingAndFeedback(driverId, driverDTO.getRating(), driverDTO.getFeedback());
        return new ResponseEntity<>(responseMessage, HttpStatus.valueOf(responseMessage.getCode()));
    }

}
