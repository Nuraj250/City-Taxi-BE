package com.example.city_Taxi.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class VehicleDTO implements Serializable {

    private Long id;
    private String model;
    private String make;
    private String registrationNumber;
    private String licensePlateNumber;
    private String vehicleType;
    private String Year;
    private int userID;
    private String image1;
    private String image2;
}
