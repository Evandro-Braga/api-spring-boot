package com.api.cars.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.cars.models.CarsModel;
import com.api.cars.repositories.CarsRepository;

@Service
public class CarsService {
    
    @Autowired
    final CarsRepository carsRepository;

    public CarsService(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Transactional
    public CarsModel save(CarsModel carsModel) {
        return carsRepository.save(carsModel);
    }

    public List<CarsModel> findAll() {
        return carsRepository.findAll();
    }

    public Optional<CarsModel> findById(UUID id) {
        return carsRepository.findById(id);
    }

    @Transactional
    public void delete(CarsModel carsModel) {
        carsRepository.delete(carsModel);
    }
}
