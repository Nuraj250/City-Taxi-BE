package com.example.city_Taxi.mapper;

import com.example.city_Taxi.dto.TripDTO;
import com.example.city_Taxi.model.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripMapper {

   TripMapper INSTANCE =Mappers.getMapper(TripMapper.class);

   TripDTO tripToTripDTO(Trip trip);

   Trip tripDTOToTrip(TripDTO tripDTO);
}
