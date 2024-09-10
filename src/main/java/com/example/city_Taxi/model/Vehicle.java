package com.example.city_Taxi.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String registrationNumber;

    @Column(nullable = false)
    private String licensePlateNumber;

    @Column(nullable = false)
    private String vehicleType;

    @Column(nullable = false)
    private String Year;

    @Column(nullable = false)
    private Long userId;

    @Lob
    @Column(name = "image1", nullable = false)
    private byte[] image1;

    @Lob
    @Column(name = "image2", nullable = false)
    private byte[] image2;
}
