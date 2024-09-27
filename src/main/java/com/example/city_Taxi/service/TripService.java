package com.example.city_Taxi.service;

import com.example.city_Taxi.dto.TripDTO;
import com.example.city_Taxi.util.ResponseMessage;

public interface TripService {

    ResponseMessage bookTrip(TripDTO tripDTO);

    ResponseMessage startTrip(Long tripId, Long driverId);

    ResponseMessage completeTrip(Long tripId);

    ResponseMessage cancelTrip(Long tripId);

    ResponseMessage getTripDetailsById(Long tripId);

    ResponseMessage getAllTrip(Long userId, boolean IsDriver);

    ResponseMessage searchTrip(Double startLatitude, Double startLongitude, Double distance);
}
