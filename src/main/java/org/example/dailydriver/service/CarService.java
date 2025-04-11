package org.example.dailydriver.service;

import org.example.dailydriver.model.dto.carDto.CarCreateDto;
import org.example.dailydriver.model.dto.carDto.CarDto;
import org.example.dailydriver.model.dto.carDto.CarUpdateDto;
import org.example.dailydriver.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService implements CrudService<CarCreateDto, CarUpdateDto, CarDto, String>{

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Boolean save(CarCreateDto entity) {
        return null;
    }

    @Override
    public CarDto update(CarCreateDto entity, String id) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }

    @Override
    public CarDto findById(String id) {
        return null;
    }

    @Override
    public List<CarDto> findAll() {
        return List.of();
    }
}
