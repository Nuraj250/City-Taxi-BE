package com.example.city_Taxi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double discountPercentage;

    @Column(nullable = false)
    private String promotionType;

    @Column(nullable = true)
    private LocalDateTime validFrom;

    @Column(nullable = true)
    private LocalDateTime validTo;

    @Column(nullable = true)
    private Integer requiredRides;

    @Column(nullable = false)
    private Double minimumFare;
}
