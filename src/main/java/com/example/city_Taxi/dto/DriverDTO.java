package com.example.city_Taxi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class DriverDTO implements Serializable {

    private String driverState; // Either 'available' or 'busy'
    private double rating; // New rating
    private String feedback; // Feedback from customer

}
