package com.example.city_Taxi.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingRequestDTO implements Serializable {
    private UserDTO user;
    private TripDTO trip;
}
