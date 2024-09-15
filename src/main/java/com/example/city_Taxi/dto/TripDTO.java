package com.example.city_Taxi.dto;

import lombok.*;
import org.springframework.data.geo.Point;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class TripDTO implements Serializable {

    private Long passengerId;
    private Long driverId;
    private double startLatitude;
    private double startLongitude;
    private double endLatitude;
    private double endLongitude;
    private LocalDateTime bookingTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private Double fare;
}
