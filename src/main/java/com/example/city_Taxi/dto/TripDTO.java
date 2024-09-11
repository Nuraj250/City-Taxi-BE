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

    private Long userId;
    private Long driverId;
    private Point pickupLocation;
    private Point dropOffLocation;
    private LocalDateTime bookingTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private Double fare;
}
