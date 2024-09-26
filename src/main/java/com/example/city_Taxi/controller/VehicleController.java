package com.example.city_Taxi.controller;

import com.example.city_Taxi.dto.VehicleDTO;
import com.example.city_Taxi.service.VehicleService;
import com.example.city_Taxi.util.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("v1/vehicles")
@CrossOrigin(origins = "*")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/create")
    public ResponseEntity<?> addVehicle(@RequestBody VehicleDTO vehicleDTO, @RequestParam Long userId) {
        ResponseMessage addVehicle = vehicleService.creatVehicle(vehicleDTO, userId);
        log.info("Vehicle found {}", addVehicle);
        return new ResponseEntity<>(addVehicle, HttpStatusCode.valueOf(addVehicle.getCode()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO vehicleDTO) {
        ResponseMessage updateVehicle = vehicleService.updateVehicle(id, vehicleDTO);
        log.info("Vehicle is updated {}", updateVehicle);
        return new ResponseEntity<>(updateVehicle, HttpStatusCode.valueOf(updateVehicle.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        ResponseMessage deleteVehicle = vehicleService.deleteVehicle(id);
        log.info("Vehicle is deleted {}", deleteVehicle);
        return new ResponseEntity<>(deleteVehicle, HttpStatusCode.valueOf(deleteVehicle.getCode()));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllVehicles() {
        ResponseMessage getAllVehicles = vehicleService.getAllVehicles();
        log.info("list of All Users {}", getAllVehicles);
        return new ResponseEntity<>(getAllVehicles, HttpStatusCode.valueOf(getAllVehicles.getCode()));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getVehiclesByUserId(@PathVariable Long userId) {
        ResponseMessage getVehiclesByUserId = vehicleService.getVehiclesByUser(userId);
        log.info("Vehicle IS here {}", getVehiclesByUserId);
        return new ResponseEntity<>(getVehiclesByUserId, HttpStatusCode.valueOf(getVehiclesByUserId.getCode()));
    }

}
