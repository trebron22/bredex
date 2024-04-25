package com.bredex.usedcarsearch.model.repository;

import com.bredex.usedcarsearch.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Modifying
    @Query("DELETE FROM Car c WHERE c.id = :carId AND c.user.id = :userId")
    void deleteCarByIdAndUserId(Long carId, Long userId);

    List<Car> findByBrandContainingIgnoreCaseAndTypeContainingIgnoreCaseAndPriceLessThanEqual(
            String brand, String type, Long price);
}
