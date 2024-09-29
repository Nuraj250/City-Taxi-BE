package com.example.city_Taxi.service;

import com.example.city_Taxi.model.Promotion;
import com.example.city_Taxi.util.ResponseMessage;

import java.time.LocalDateTime;

public interface PromotionService {
    ResponseMessage getAllPromotions();

    ResponseMessage getPromotionById(Long id);

    ResponseMessage addPromotion(Promotion promotion);

    ResponseMessage updatePromotion(Long id, Promotion promotion);

    ResponseMessage deletePromotion(Long id);

    ResponseMessage getValidPromotions(String promotionType, LocalDateTime bookingTime);

    ResponseMessage getTimeBasedPromotions(LocalDateTime bookingTime);


}
