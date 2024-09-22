package com.example.city_Taxi.service;

import com.example.city_Taxi.dto.VehicleDTO;
import com.example.city_Taxi.mapper.VehicleMapper;
import com.example.city_Taxi.model.User;
import com.example.city_Taxi.model.Vehicle;
import com.example.city_Taxi.repository.VehicleRepository;
import com.example.city_Taxi.util.Alert;
import com.example.city_Taxi.util.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;

    @Override
    public ResponseMessage creatVehicle(VehicleDTO vehicleDto, Long id) {
        Vehicle vehicle = VehicleMapper.INSTANCE.vehicleDTOToVehicle(vehicleDto);
        vehicle.setUserId(id);
        Vehicle save = vehicleRepository.save(vehicle);
        if (save != null) {
            return new ResponseMessage(200, Alert.saveSuccess, vehicle);
        }
        return new ResponseMessage(201, Alert.saveFailed, null);
    }

    @Override
    public ResponseMessage updateVehicle(Long id, VehicleDTO vehicleDto) {
        return vehicleRepository.findById(id).map(vehicle -> {
            vehicle.setModel(vehicleDto.getModel());
            vehicle.setLicensePlateNumber(vehicleDto.getLicensePlateNumber());
            vehicle.setVehicleType(vehicleDto.getVehicleType());
            vehicle.setImage1(vehicleDto.getImage1().getBytes());
            vehicle.setImage2(vehicleDto.getImage2().getBytes());
            vehicleRepository.save(vehicle);
            return new ResponseMessage(200, Alert.updateSuccess, vehicle);
        }).orElse(new ResponseMessage(404, Alert.nosuchfound, null));
    }

    @Override
    public ResponseMessage getVehiclesByUser(Long userId) {
        List<Vehicle> vehicles = vehicleRepository.findAllByUserId(userId);
        if (vehicles.isEmpty()) {
            return new ResponseMessage(404, Alert.nosuchfound, null);
        }return new ResponseMessage(200, Alert.ok, vehicles);
    }

    @Override
    public ResponseMessage deleteVehicle(Long id) {
        return vehicleRepository.findById(id).map(vehicle -> {
            vehicleRepository.delete(vehicle);
            return new ResponseMessage(200, Alert.removeSuccess, null);
        }). orElse(new ResponseMessage(404, Alert.nosuchfound, null));

    }

    @Override
    public ResponseMessage getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        if (vehicles.isEmpty()) {
            return new ResponseMessage(404, Alert.nosuchfound, null);
        }
        return new ResponseMessage(200, Alert.ok, vehicles);
    }

    @Override
    public ResponseMessage getVehicleById(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return vehicle.map(value -> new ResponseMessage(200, Alert.ok, value)).orElseGet(() -> new ResponseMessage(404, Alert.nosuchfound, null));
    }
}
