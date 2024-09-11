package com.example.city_Taxi.service;

import com.example.city_Taxi.dto.TripDTO;
import com.example.city_Taxi.mapper.TripMapper;
import com.example.city_Taxi.model.Trip;
import com.example.city_Taxi.repository.TripRepository;
import com.example.city_Taxi.repository.UserRepository;
import com.example.city_Taxi.util.Alert;
import com.example.city_Taxi.util.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class TripServiceImpl implements TripService{

    @Autowired
    private TripRepository tripRepository;

    @Override
    public ResponseMessage bookTrip(TripDTO tripDTO) {
        try {
        Trip trip = TripMapper.INSTANCE.tripDTOToTrip(tripDTO);
        trip.setStatus("BOOKED");
        trip.setBookingTime(LocalDateTime.now());
        trip.setDriverId(null);
        tripRepository.save(trip);
        return new ResponseMessage(200, Alert.saveSuccess, trip);
        }catch (Exception e){
            return new ResponseMessage(500, Alert.saveFailed, null);
        }
    }

    @Override
    public ResponseMessage startTrip(Long tripId, Long driverId) {
        return tripRepository.findById(tripId).map(trip -> {
            if ("Booked".equals(trip.getStatus())){
                trip.setDriverId(driverId);
                trip.setStartTime(LocalDateTime.now());
                trip.setStatus("STARTED");
                tripRepository.save(trip);
                return new ResponseMessage(200,Alert.updateSuccess, null);

            }
            return new ResponseMessage(200, "Something went Wrong, Try Again Later", null);

        }).orElse(new ResponseMessage(200,Alert.nosuchfound, null));
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
                return new ResponseMessage(200, "Trip completed successfully", trip);
            }
            return new ResponseMessage(400, "Trip must be in STARTED status, Try Again", null);
        }).orElse(new ResponseMessage(404, Alert.nosuchfound, null));
    }



    @Override
    public ResponseMessage cancelTrip(Long tripId) {
        return tripRepository.findById(tripId).map(trip -> {
            if ("Booked".equals(trip.getStatus())){
                trip.setStartTime(LocalDateTime.now());
                trip.setStatus("CANCELLED");
                tripRepository.save(trip);
                return new ResponseMessage(200,"Trip Canceled Successfully", null);

            }
            return new ResponseMessage(200, "Something went Wrong, Try Again Later", null);

        }).orElse(new ResponseMessage(200,Alert.nosuchfound, null));
    }

    @Override
    public ResponseMessage getTripDetailsById(Long tripId) {
        return tripRepository.findById(tripId)
                .map(trip -> new ResponseMessage(200, Alert.ok, trip))
                .orElse(new ResponseMessage(404, Alert.nosuchfound, null));
    }

    @Override
    public ResponseMessage getAllTrip(Long userId, boolean IsDriver) {
        List<Trip> trips = IsDriver ? tripRepository.findByDriverId(userId) : tripRepository.findByPassengerId(userId);
        return new ResponseMessage(200, Alert.ok, trips);

    }

    private double calculateFare(Trip trip) {
        return 0;
    }
}
