package com.example.city_Taxi.controller;

import com.example.city_Taxi.dto.BookingRequestDTO;
import com.example.city_Taxi.dto.TripDTO;
import com.example.city_Taxi.dto.UserDTO;
import com.example.city_Taxi.model.User;
import com.example.city_Taxi.service.TripService;
import com.example.city_Taxi.service.UserService;
import com.example.city_Taxi.util.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("v1/trips")
@Slf4j
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private UserService userService;

    @PostMapping("/book")
    public ResponseEntity<?> bookTrip(@RequestBody TripDTO tripDTO) {
        ResponseMessage bookTrip = tripService.bookTrip(tripDTO);
        log.info("Trip Is Booked {}", bookTrip);
        return new ResponseEntity<>(bookTrip, HttpStatusCode.valueOf(bookTrip.getCode()));
    }

    @PostMapping("/operator/booking")
    public ResponseEntity<?> opeartorbooking(@RequestBody BookingRequestDTO bookingRequestDTO){
        // Extract UserDTO and TripDTO from the request
        UserDTO userDTO = bookingRequestDTO.getUser();
        TripDTO tripDTO = bookingRequestDTO.getTrip();
        // Save the user details (this user is not registered yet)
        ResponseMessage userResponse = userService.registerUserByOperator(userDTO);
        // Use the generated user ID for the trip if necessary
        if (userResponse.getCode() == 200) {
            User user = (User) userResponse.getObject();
            tripDTO.setPassengerId(user.getId()); // Assuming the registered user is the passenger

            // Book the trip
            ResponseMessage tripResponse = tripService.bookTrip(tripDTO);
            log.info("Unregistered user's trip is booked by operator {}", tripResponse);

            return new ResponseEntity<>(tripResponse, HttpStatusCode.valueOf(tripResponse.getCode()));
        } else {
            return new ResponseEntity<>(userResponse, HttpStatusCode.valueOf(userResponse.getCode()));
        }
    }

    @PutMapping("/start/{tripId}")
    public ResponseEntity<?> startTrip(@PathVariable Long tripId, @RequestBody Map<String, Object> body) {
        System.out.println(tripId);

        Long driverId = Long.valueOf(body.get("driverId").toString()); // Extract driverId from the body map
        System.out.println(driverId);

        ResponseMessage startTrip = tripService.startTrip(tripId, driverId);
        log.info("Trip IS Started {}", startTrip);
        return new ResponseEntity<>(startTrip, HttpStatusCode.valueOf(startTrip.getCode()));
    }

    @PutMapping("/end/{tripId}")
    public ResponseEntity<?> endTrip(@PathVariable Long tripId) {
        ResponseMessage endTrip = tripService.completeTrip(tripId);
        log.info("Trip IS ended {}", endTrip);
        return new ResponseEntity<>(endTrip, HttpStatusCode.valueOf(endTrip.getCode()));
    }

    @PutMapping("/cancel/{tripId}")
    public ResponseEntity<?> cancelTrip(@PathVariable Long tripId) {
        ResponseMessage cancelTrip = tripService.cancelTrip(tripId);
        log.info("Trip IS canceled {}", cancelTrip);
        return new ResponseEntity<>(cancelTrip, HttpStatusCode.valueOf(cancelTrip.getCode()));
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<?> getTripDetailsById(@PathVariable Long tripId) {
        ResponseMessage getTripDetailsById = tripService.getTripDetailsById(tripId);
        log.info("Trip IS here {}", getTripDetailsById);
        return new ResponseEntity<>(getTripDetailsById, HttpStatusCode.valueOf(getTripDetailsById.getCode()));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllTrips(@PathVariable Long userId, @RequestParam boolean isDriver) {
        ResponseMessage getAllTrips = tripService.getAllTrip(userId, isDriver);
        log.info("All the Trips {}", getAllTrips);
        return new ResponseEntity<>(getAllTrips, HttpStatusCode.valueOf(getAllTrips.getCode()));
    }

    @GetMapping("/searchTrip")
    public ResponseEntity<?> searchTrip(@RequestBody Map<String, Double> requestData) {
        Double startLatitude = requestData.get("startLatitude");
        Double startLongitude = requestData.get("startLongitude");
        Double distance = requestData.get("distance");

        ResponseMessage searchTrip = tripService.searchTrip(startLatitude, startLongitude, distance);
        log.info("Trip searched {}", searchTrip);
        return new ResponseEntity<>(searchTrip, HttpStatusCode.valueOf(searchTrip.getCode()));
    }
}
