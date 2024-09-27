package com.example.city_Taxi.service;

import com.example.city_Taxi.dto.TripDTO;
import com.example.city_Taxi.util.ResponseMessage;

public interface DriverService {

    ResponseMessage getDriverById(Long driverId);

    ResponseMessage updateDriverState(Long driverId, String state);

    ResponseMessage addRatingAndFeedback(Long driverId, double rating, String feedback);
}
