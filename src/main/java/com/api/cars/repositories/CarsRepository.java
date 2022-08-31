package com.api.cars.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.cars.models.CarsModel;

@Repository
public interface CarsRepository extends JpaRepository<CarsModel, UUID>{
    
}