package com.example.city_Taxi.service;

import com.example.city_Taxi.dto.TripDTO;
import com.example.city_Taxi.mapper.TripMapper;
import com.example.city_Taxi.model.Trip;
import com.example.city_Taxi.model.User;
import com.example.city_Taxi.repository.TripRepository;
import com.example.city_Taxi.repository.UserRepository;
import com.example.city_Taxi.util.Alert;
import com.example.city_Taxi.util.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class TripServiceImpl implements TripService {

    private final GeometryFactory geometryFactory = new GeometryFactory();

    private TripRepository tripRepository;

    private final TwilioService twilioService;

    private UserRepository userRepository;  // Assuming you have a UserRepository to fetch user details

    @Override
    public ResponseMessage bookTrip(TripDTO tripDTO) {
        try {
            // Fetch the user's phone number based on passengerId
            Long passengerId = tripDTO.getPassengerId();
            User passenger = userRepository.findById(passengerId).orElseThrow(() -> new RuntimeException("Passenger not found with ID: " + passengerId));

            String userPhoneNumber = passenger.getContact();  // Get the phone number from the Passenger/User entity
            Trip trip = TripMapper.INSTANCE.tripDTOToTrip(tripDTO);
            Point startPoint = mapToPoint(tripDTO.getStartLongitude(), tripDTO.getStartLatitude());
            Point endPoint = mapToPoint(tripDTO.getEndLongitude(), tripDTO.getEndLatitude());
            trip.setStartLocation(startPoint);
            trip.setStartLocation(endPoint);
            trip.setStatus("BOOKED");
            trip.setBookingTime(LocalDateTime.now());
            trip.setDriverId(null);
            tripRepository.save(trip);

            // Send an SMS to the user after successfully booking the trip
            String messageBody = "Your trip has been successfully booked! Please wait until a driver selects your trip.";

            // Send SMS using TwilioService
            twilioService.sendSMS(userPhoneNumber, messageBody);

            return new ResponseMessage(500, Alert.saveSuccess, trip);
        } catch (Exception e) {
            return new ResponseMessage(500, Alert.saveFailed, null);
        }
    }

    @Override
    public ResponseMessage startTrip(Long tripId, Long driverId) {
        return tripRepository.findById(tripId).map(trip -> {
            if ("Booked".equals(trip.getStatus())) {
                trip.setDriverId(driverId);
                trip.setStartTime(LocalDateTime.now());
                trip.setStatus("STARTED");

                Long passengerId = trip.getPassengerId();
                User passenger = userRepository.findById(passengerId).orElseThrow(() -> new RuntimeException("Passenger not found with ID: " + passengerId));

                String username = passenger.getUsername();
                String passengerPhoneNumber = passenger.getContact();  // Get passenger's phone number

                User driver = userRepository.findById(driverId).orElseThrow(() -> new RuntimeException("Driver not found with ID: " + driverId));

                String driverName = driver.getUsername();  // Get driver's name
                String driverPhoneNumber = driver.getContact();  // Get driver's phone number

                tripRepository.save(trip);
                String messageBody = "Your trip has started! Your driver is " + driverName + " (Phone: " + driverPhoneNumber + "). Have a safe trip!";

                String driverMessageBody = "You have been selected for a trip! Your driver is " + username + " (Phone: " + passengerPhoneNumber + "). Please proceed to the pickup location.";

                twilioService.sendSMS(passengerPhoneNumber, messageBody);
                twilioService.sendSMS(driverPhoneNumber, driverMessageBody);

                return new ResponseMessage(200, Alert.updateSuccess, null);

            }
            return new ResponseMessage(200, "Something went Wrong, Try Again Later", null);

        }).orElse(new ResponseMessage(200, Alert.nosuchfound, null));
    }

    @Override
    public ResponseMessage completeTrip(Long tripId) {
        return tripRepository.findById(tripId).map(trip -> {
            if ("STARTED".equals(trip.getStatus())) {
                trip.setEndTime(LocalDateTime.now());
                trip.setStatus("COMPLETED");
                double fare = calculateFare(trip);
                trip.setFare(fare);
                tripRepository.save(trip);
                Long passengerId = trip.getPassengerId();
                User passenger = userRepository.findById(passengerId)
                        .orElseThrow(() -> new RuntimeException("Passenger not found with ID: " + passengerId));

                String passengerPhoneNumber = passenger.getContact();  // Get passenger's phone number

                // Fetch the driver details using the driverId
                Long driverId = trip.getDriverId();
                User driver = userRepository.findById(driverId)
                        .orElseThrow(() -> new RuntimeException("Driver not found with ID: " + driverId));

                String driverPhoneNumber = driver.getContact();  // Get driver's phone number

                String messageBody = "Your trip has been completed. The total fare is $" + fare + ". Thank you for choosing our service!";

                String driverMessageBody = "The trip has been completed. The total fare is $" + fare + ". Please review the fare and complete the necessary steps.";

                twilioService.sendSMS(driverPhoneNumber, driverMessageBody);
                twilioService.sendSMS(passengerPhoneNumber, messageBody);
                return new ResponseMessage(200, "Trip completed successfully", trip);
            }
            return new ResponseMessage(400, "Trip must be in STARTED status, Try Again", null);
        }).orElse(new ResponseMessage(404, Alert.nosuchfound, null));
    }

    @Override
    public ResponseMessage cancelTrip(Long tripId) {
        return tripRepository.findById(tripId).map(trip -> {
            if ("Booked".equals(trip.getStatus())) {
                trip.setStartTime(LocalDateTime.now());
                trip.setStatus("CANCELLED");
                tripRepository.save(trip);

                Long passengerId = trip.getPassengerId();
                User passenger = userRepository.findById(passengerId)
                        .orElseThrow(() -> new RuntimeException("Passenger not found with ID: " + passengerId));

                String passengerPhoneNumber = passenger.getContact();

                // Send SMS to the passenger about trip cancellation
                String messageBody = "Your trip has been canceled. If this is an error, please contact our support team.";

                // Send SMS using TwilioService
                twilioService.sendSMS(passengerPhoneNumber, messageBody);
                return new ResponseMessage(200, Alert.updateSuccess, trip);

            }
            return new ResponseMessage(200, Alert.updateFailed, null);

        }).orElse(new ResponseMessage(200, Alert.nosuchfound, null));
    }

    @Override
    public ResponseMessage getTripDetailsById(Long tripId) {
        return tripRepository.findById(tripId).map(trip -> new ResponseMessage(200, "User found", trip)) // Status code 200 for success
                .orElseGet(() -> new ResponseMessage(404, "User not found", null)); // Status code 404 for not found
    }

    @Override
    public ResponseMessage getAllTrip(Long userId, boolean IsDriver) {
        List<Trip> trips = IsDriver ? tripRepository.findByDriverId(userId) : tripRepository.findByPassengerId(userId);
        if (trips.isEmpty()) {
            return new ResponseMessage(404, "Not found", null);
        }
        return new ResponseMessage(200, Alert.ok, trips);
    }

    @Override
    public ResponseMessage searchTrip(Double startLatitude, Double startLongitude, Double distance) {
        List<Trip> searchTrip = tripRepository.searchTrip(startLongitude, startLongitude, distance);
        if (!(searchTrip == null)) {
            return new ResponseMessage(200, Alert.ok, searchTrip);
        }
        return new ResponseMessage(404, "No Trip found", null);
    }

    private double calculateFare(Trip trip) {
        return 0;
    }

    private Point mapToPoint(final double longitude, final double latitude) {
        Coordinate coordinate = new Coordinate(longitude, latitude);
        Point point = geometryFactory.createPoint(coordinate);
        point.setSRID(4326);
        return point;
    }
}
