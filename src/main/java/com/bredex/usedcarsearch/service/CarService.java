package com.bredex.usedcarsearch.service;

import com.bredex.usedcarsearch.controller.dto.CarAddRequest;
import com.bredex.usedcarsearch.controller.dto.CarAddResponse;
import com.bredex.usedcarsearch.controller.dto.CarSearchRequest;
import com.bredex.usedcarsearch.model.Car;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarService {
    ResponseEntity<CarAddResponse> add(CarAddRequest carAddRequest);

    ResponseEntity<Long> delete(Long id, Long userId);

    ResponseEntity<Car> getCar(Long id);

    ResponseEntity<List<String>> searchAds(CarSearchRequest request);
}
