package com.example.city_Taxi.service;

import com.example.city_Taxi.dto.VehicleDTO;
import com.example.city_Taxi.util.ResponseMessage;

public interface VehicleService {

    ResponseMessage  creatVehicle(VehicleDTO vehicleDto, Long id);

    ResponseMessage updateVehicle(Long id, VehicleDTO vehicleDto);

    ResponseMessage getVehiclesByUser(Long userId);

    ResponseMessage deleteVehicle(Long id);
}
