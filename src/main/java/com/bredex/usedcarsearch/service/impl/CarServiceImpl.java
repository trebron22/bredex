package com.bredex.usedcarsearch.service.impl;

import com.bredex.usedcarsearch.controller.dto.CarAddRequest;
import com.bredex.usedcarsearch.controller.dto.CarAddResponse;
import com.bredex.usedcarsearch.controller.dto.CarSearchRequest;
import com.bredex.usedcarsearch.model.Car;
import com.bredex.usedcarsearch.model.User;
import com.bredex.usedcarsearch.model.repository.CarRepository;
import com.bredex.usedcarsearch.model.repository.UserRepository;
import com.bredex.usedcarsearch.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private static final Logger logger = LogManager.getLogger(CarServiceImpl.class);

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public ResponseEntity<CarAddResponse> add(CarAddRequest carAddRequest) {
        logger.info("method add called with carAddRequest {}", carAddRequest);
        try {
            User user = userRepository.findById(carAddRequest.getUserId()).get();
            Car car = Car.builder()
                    .brand(carAddRequest.getBrand())
                    .type(carAddRequest.getType())
                    .price(carAddRequest.getPrice())
                    .description(carAddRequest.getDescription())
                    .user(user)
                    .build();

            Car savedCar = carRepository.save(car);

            return ResponseEntity.ok(new CarAddResponse(savedCar.id));
        } catch (Exception e) {
            logger.error("Saved car failed {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CarAddResponse(-1l));
        }


    }

    @Override
    @Transactional
    public ResponseEntity<Long> delete(Long id, Long userId) {
        logger.info("method delete called with id {}", id);

        try {
            Car car = carRepository.findById(id).orElse(null);

            if (car == null) {
                return ResponseEntity.notFound().build();
            }

            carRepository.deleteCarByIdAndUserId(id, userId);
            return ResponseEntity.ok(id);

        } catch (Exception e) {
            logger.error("Failed to delete car with id {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(id);
        }
    }

    @Override
    public ResponseEntity<Car> getCar(Long id) {
        logger.info("method getCar called with id {}", id);
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            return ResponseEntity.ok(car);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<List<String>> searchAds(CarSearchRequest request) {
        logger.info("method searchAds called with carSearchRequest {}", request);
        List<Car> cars = carRepository.findByBrandContainingIgnoreCaseAndTypeContainingIgnoreCaseAndPriceLessThanEqual(
                request.getBrand(), request.getType(), request.getPrice());

        if (!cars.isEmpty()) {
            List<String> urls = new ArrayList<>();
            for (Car car : cars) {
                String url = buildAdUrl(car.getId());
                urls.add(url);
            }
            return ResponseEntity.ok(urls);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private String buildAdUrl(Long carId) {
        return "/car/ad/" + carId;
    }

}
