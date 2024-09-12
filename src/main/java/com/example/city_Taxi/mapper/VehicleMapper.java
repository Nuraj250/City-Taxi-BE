package com.example.city_Taxi.mapper;


import com.example.city_Taxi.dto.VehicleDTO;
import com.example.city_Taxi.model.Vehicle;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    @Mapping(target = "image1", expression = "java(stringToByteArray(vehicleDTO.getImage1()))")
    @Mapping(target = "image2", expression = "java(stringToByteArray(vehicleDTO.getImage2()))")
    Vehicle vehicleDTOToVehicle(VehicleDTO vehicleDTO);

    @Mapping(target = "image1", expression = "java(byteArrayToString(vehicle.getImage1()))")
    @Mapping(target = "image2", expression = "java(byteArrayToString(vehicle.getImage2()))")
    VehicleDTO vehicleToVehicleDTO(Vehicle vehicle);

    // Custom method to convert String (path) to byte[]
    default byte[] stringToByteArray(String imagePath) {
        try {
            return Files.readAllBytes(Paths.get(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Handle the exception properly
        }
    }

    // Custom method to convert byte[] to String (path)
    default String byteArrayToString(byte[] imageData) {
        if (imageData != null) {
            return new String(imageData); // Convert to string if necessary, or return actual file path
        }
        return null;
    }
}
