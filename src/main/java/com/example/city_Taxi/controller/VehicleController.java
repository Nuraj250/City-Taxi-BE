package com.example.city_Taxi.controller;

import com.example.city_Taxi.dto.VehicleDTO;
import com.example.city_Taxi.service.VehicleService;
import com.example.city_Taxi.util.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseMessage addVehicle(@RequestBody VehicleDTO vehicleDTO, @RequestParam Long userId) {
        return vehicleService.creatVehicle(vehicleDTO, userId);
    }

    @GetMapping("/user/{userId}")
    public ResponseMessage getVehiclesByUserId(@PathVariable Long userId) {
        return vehicleService.getVehiclesByUser(userId);
    }

    @PutMapping("/{id}")
    public ResponseMessage updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO) {
        return vehicleService.updateVehicle(id, vehicleDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage deleteVehicle(@PathVariable Long id) {
        return vehicleService.deleteVehicle(id);
    }
}
