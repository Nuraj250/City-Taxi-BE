package com.example.city_Taxi.mapper;


import com.example.city_Taxi.dto.VehicleDTO;
import com.example.city_Taxi.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehicleMapper {

    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    @Mapping(source = "model", target = "model")
    @Mapping(source = "licensePlate", target = "licensePlate")
    @Mapping(source = "vehicleType", target = "vehicleType")
    @Mapping(source = "image1", target = "image1")
    @Mapping(source = "image2", target = "image2")
    VehicleDTO vehicleToVehicleDTO(VehicleDTO vehicle);

    @Mapping(target = "createdDate", ignore = true) // Auto-generated
    Vehicle vehicleDTOToVehicle(VehicleDTO vehicleDTO);
}
