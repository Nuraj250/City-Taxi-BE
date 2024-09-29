package com.example.city_Taxi.service.Implementaion;

import com.example.city_Taxi.model.Promotion;
import com.example.city_Taxi.model.User;
import com.example.city_Taxi.repository.PromotionRepository;
import com.example.city_Taxi.service.PromotionService;
import com.example.city_Taxi.util.Alert;
import com.example.city_Taxi.util.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class PromotionServiceImpl implements PromotionService {

    private PromotionRepository promotionRepository;

    @Override
    public ResponseMessage getAllPromotions() {
        List<Promotion> promotions = promotionRepository.findAll();
        return new ResponseMessage(200, Alert.ok, promotions);
    }

    @Override
    public ResponseMessage getPromotionById(Long id) {
        Optional<Promotion> promotion = promotionRepository.findById(id);
        return new ResponseMessage(200, Alert.ok, promotion);
    }

    @Override
    public ResponseMessage addPromotion(Promotion promotion) {
        Promotion save = promotionRepository.save(promotion);
        return new ResponseMessage(200, Alert.ok, save);
    }

    @Override
    public ResponseMessage updatePromotion(Long id, Promotion promotion) {
        Optional<Promotion> existingPromotion = promotionRepository.findById(id);
        if (existingPromotion.isPresent()) {
            promotion.setId(id);
            return new ResponseMessage(200, Alert.updateSuccess, promotion);
        }
        return new ResponseMessage(200, Alert.updateFailed, null);
    }

    @Override
    public ResponseMessage deletePromotion(Long id) {
        return promotionRepository.findById(id).map(promotion -> {
            promotionRepository.delete(promotion);
            return new ResponseMessage(200, Alert.removeSuccess, null);
        }).orElse(new ResponseMessage(404, Alert.nosuchfound, null));
    }

    @Override
    public ResponseMessage getValidPromotions(String promotionType, LocalDateTime bookingTime) {
        List<Promotion> allByPromotionType = promotionRepository.findAllByPromotionType(promotionType);
        return new ResponseMessage(200, Alert.ok, allByPromotionType);
    }

    @Override
    public ResponseMessage getTimeBasedPromotions(LocalDateTime bookingTime) {
        List<Promotion> allByValidFromBeforeAndValidToAfter = promotionRepository.findAllByValidFromBeforeAndValidToAfter(bookingTime, bookingTime);
        return new ResponseMessage(200, Alert.ok, allByValidFromBeforeAndValidToAfter);
    }
}
