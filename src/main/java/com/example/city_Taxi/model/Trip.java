package com.example.city_Taxi.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.geo.Point;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = true)
    private Long driverId;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point startLocation;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point endLocation;

    @Column(nullable = true)
    private LocalDateTime bookingTime;

    @Column(nullable = true)
    private LocalDateTime startTime;

    @Column(nullable = true)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private String status;

    @Column(nullable = true)
    private Double fare;
}
