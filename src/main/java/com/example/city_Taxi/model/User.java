package com.example.city_Taxi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
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
    private String userType; // "Driver", "Customer", "Admin"

    @Column(nullable = true)
    private String profileImage; // Image path

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
}
