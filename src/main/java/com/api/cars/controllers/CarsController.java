package com.api.cars.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.cars.dtos.CarsDto;
import com.api.cars.models.CarsModel;
import com.api.cars.services.CarsService;

@RestController
@RequestMapping("/cars")
public class CarsController {

    final CarsService carsService;

    public CarsController(CarsService carsService) {
        this.carsService = carsService;
    }

    @PostMapping
    public ResponseEntity<Object> saveCars(@RequestBody @Valid CarsDto carsDto) {
        var carsModel = new CarsModel();
        BeanUtils.copyProperties(carsDto, carsModel);
        carsModel.setData(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(carsService.save(carsModel));
    }

    @GetMapping
    public ResponseEntity<List<CarsModel>> getAllCars() {
        return ResponseEntity.status(HttpStatus.OK).body(carsService.findAll());
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> getOneCar(@PathVariable(value = "id") UUID id) {
        Optional<CarsModel> CarsModelOptional = carsService.findById(id);
        if(!CarsModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(CarsModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
        Optional<CarsModel> CarsModelOptional = carsService.findById(id);
        if(!CarsModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado!");
        }
        carsService.delete(CarsModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Carro deletado com sucesso!");
    }
}
