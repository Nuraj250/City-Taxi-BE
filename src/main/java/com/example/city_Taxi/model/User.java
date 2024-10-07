package com.example.city_Taxi.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_user")
public class User implements Serializable {
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
    private Double rating = 0.0; // Average rating for the driver

    @Column(nullable = true)
    private Integer totalRatings = 0; // The number of ratings the driver has received

    @Column(nullable = true)
    private String feedback; // The feedback received by customers

    @Column(nullable = true)
    private String driverState; // Either 'available' or 'busy'
}

