package com.example.city_Taxi.mapper;


import com.example.city_Taxi.dto.VehicleDTO;
import com.example.city_Taxi.model.Vehicle;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    VehicleDTO vehicleToVehicleDTO(VehicleDTO vehicle);

    Vehicle vehicleDTOToVehicle(VehicleDTO vehicleDTO);
}
