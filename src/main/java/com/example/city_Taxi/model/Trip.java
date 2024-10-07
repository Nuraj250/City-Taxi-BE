package com.example.city_Taxi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;
import org.n52.jackson.datatype.jts.GeometryDeserializer;
import org.n52.jackson.datatype.jts.GeometrySerializer;

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
    private Long passengerId;

    @Column(nullable = true)
    private Long driverId;

    @Column(name = "start_location")
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(using = GeometryDeserializer.class)
    private Point startLocation;

    @Column(name = "end_location")
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(using = GeometryDeserializer.class)
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

    @Column(nullable = true)
    private int distant;
}
