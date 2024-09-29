package com.example.city_Taxi.repository;

import com.example.city_Taxi.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findAllByPromotionType(String promotionType);
    List<Promotion> findAllByValidFromBeforeAndValidToAfter(LocalDateTime from, LocalDateTime to);
}