package com.bredex.usedcarsearch.controller;

import com.bredex.usedcarsearch.controller.dto.CarAddRequest;
import com.bredex.usedcarsearch.controller.dto.CarAddResponse;
import com.bredex.usedcarsearch.controller.dto.CarSearchRequest;
import com.bredex.usedcarsearch.controller.dto.DeleteRequest;
import com.bredex.usedcarsearch.model.Car;
import com.bredex.usedcarsearch.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    private static final Logger logger = LogManager.getLogger(CarController.class);


    @Operation(summary = "Add car", description = "Add a new car")
    @PostMapping("/add")
    public ResponseEntity<CarAddResponse> add(
            @Valid @RequestBody CarAddRequest carAddRequest) {
        logger.info("Car add method called with {}", carAddRequest);
        return carService.add(carAddRequest);
    }

    @Operation(summary = "Delete car", description = "Delete a car by ID and user ID")
    @DeleteMapping("/delete")
    public ResponseEntity<Long> delete(
            @Valid @RequestBody DeleteRequest deleteRequest) {
        logger.info("Car delete method called with id {}: userId {}", deleteRequest.carId(), deleteRequest.userId());
        return carService.delete(deleteRequest.carId(), deleteRequest.userId());
    }

    @Operation(summary = "Get car", description = "Get a car by ID")
    @GetMapping("/ad/{id}")
    public ResponseEntity<Car> getCar(
            @Parameter(description = "Car ID") @PathVariable Long id) {
        logger.info("Car getCar method called with id {}:", id);
        return carService.getCar(id);
    }

    @Operation(summary = "Search cars", description = "Search cars by criteria")
    @GetMapping("/ad/search")
    public ResponseEntity<List<String>> searchAds(
            @Valid @RequestBody CarSearchRequest request) {
        logger.info("Car searchAds method called with car {}:", request);
        return carService.searchAds(request);
    }
}
