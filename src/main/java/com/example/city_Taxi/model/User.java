package com.example.city_Taxi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String userType; // "Driver", "Customer", "Admin"

    @Column(nullable = true)
    private String profileImage; // Image path

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(nullable = true)
    private double rating; // Average rating for the driver

    @Column(nullable = true)
    private int totalRatings; // The number of ratings the driver has received

    @Column(nullable = true)
    private String feedback; // The feedback received by customers

    @Column(nullable = true)
    private String driverState; // Either 'available' or 'busy'

}

