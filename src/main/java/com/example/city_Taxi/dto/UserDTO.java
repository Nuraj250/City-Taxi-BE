package com.example.city_Taxi.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class UserDTO implements Serializable {

    private String username;
    private String password;
    private String email;
    private String userType; // "Driver", "Customer", "Admin"
    private String profileImage; // Image URL or path
}
