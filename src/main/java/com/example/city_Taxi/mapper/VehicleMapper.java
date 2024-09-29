package com.example.city_Taxi.mapper;


import com.example.city_Taxi.dto.VehicleDTO;
import com.example.city_Taxi.model.Vehicle;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);
    Logger log = LoggerFactory.getLogger(VehicleMapper.class);

    @Mapping(target = "image1", expression = "java(stringToByteArray(vehicleDTO.getImage1()))")
    @Mapping(target = "image2", expression = "java(stringToByteArray(vehicleDTO.getImage2()))")
    Vehicle vehicleDTOToVehicle(VehicleDTO vehicleDTO);

    @Mapping(target = "image1", expression = "java(byteArrayToString(vehicle.getImage1()))")
    @Mapping(target = "image2", expression = "java(byteArrayToString(vehicle.getImage2()))")
    VehicleDTO vehicleToVehicleDTO(Vehicle vehicle);

    default byte[] stringToByteArray(String imagePath) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            log.error("Invalid image path: {}", imagePath);
            return null; // Handle invalid path case
        }

        // Trim the imagePath to avoid leading/trailing spaces
        String trimmedPath = imagePath.trim();

        try {
            return Files.readAllBytes(Paths.get(trimmedPath));
        } catch (IOException e) {
            log.error("Error reading bytes from file path: {}", trimmedPath, e);
            return null; // Handle the exception properly
        }
    }

    // Custom method to convert byte[] to String (path)
    default String byteArrayToString(byte[] imageData) {
        return null;
    }
}
