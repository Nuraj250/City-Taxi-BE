package com.example.city_Taxi.service;

import com.example.city_Taxi.model.User;
import com.example.city_Taxi.repository.DriverRepository;
import com.example.city_Taxi.util.Alert;
import com.example.city_Taxi.util.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class DriverServiceImpl implements DriverService{

    private DriverRepository driverRepository;

    @Override
    public ResponseMessage getDriverById(Long driverId) {
        try {
            User driver = driverRepository.findDriverById(driverId)
                    .orElse(null);

            if (driver == null) {
                return new ResponseMessage(404, Alert.nosuchfound, null);
            }

            return new ResponseMessage(200, Alert.ok, driver);
        } catch (Exception e) {
            return new ResponseMessage(500, Alert.backendError, null);
        }
    }

    @Override
    public ResponseMessage updateDriverState(Long driverId, String state) {
        try {
            User driver = driverRepository.findDriverById(driverId)
                    .orElse(null);

            if (driver == null) {
                return new ResponseMessage(404, Alert.nosuchfound, null);
            }

            driver.setDriverState(state);
            driverRepository.save(driver);

            return new ResponseMessage(200, Alert.ok, driver);
        } catch (Exception e) {
            return new ResponseMessage(500, Alert.saveFailed, null);
        }    }

    @Override
    public ResponseMessage addRatingAndFeedback(Long driverId, double rating, String feedback) {
        try {
            User driver = driverRepository.findDriverById(driverId)
                    .orElse(null);

            if (driver == null) {
                return new ResponseMessage(404, Alert.nosuchfound, null);
            }

            // Update the driver's rating and feedback
            double newRating = ((driver.getRating() * driver.getTotalRatings()) + rating) / (driver.getTotalRatings() + 1);
            driver.setRating(newRating);
            driver.setTotalRatings(driver.getTotalRatings() + 1);
            driver.setFeedback(driver.getFeedback() != null ? driver.getFeedback() + "\n" + feedback : feedback);

            driverRepository.save(driver);

            return new ResponseMessage(200, Alert.ok, driver);
        } catch (Exception e) {
            return new ResponseMessage(500, Alert.saveFailed, null);
        }
    }
}
