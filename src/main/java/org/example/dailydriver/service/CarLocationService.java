package org.example.dailydriver.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.dailydriver.mapper.LocationMapper;
import org.example.dailydriver.model.dto.locationDto.CarLocationDto;
import org.example.dailydriver.model.dto.locationDto.LocationCreateDto;
import org.example.dailydriver.model.entity.Car;
import org.example.dailydriver.model.entity.CarLocation;
import org.example.dailydriver.repository.CarLocationRepository;
import org.example.dailydriver.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarLocationService {

    private final CarLocationRepository repository;
    private final LocationMapper mapper;
    private final CarRepository carRepository;

    public CarLocationService(CarLocationRepository repository, LocationMapper mapper, CarRepository carRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.carRepository = carRepository;
    }


    public CarLocationDto save(LocationCreateDto entity, String carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new EntityNotFoundException("Car not found"));

        CarLocation carLocation = mapper.toEntity(entity);
        carLocation.setCar(car);
        return mapper.toDto(repository.save(carLocation));
    }

    public List<CarLocationDto> getLocationHistory(String carId) {
        List<CarLocation> list = repository.findAllByCar_IdOrderByCreatedAtDesc(carId);
        return mapper.toDtoList(list);
    }

    public CarLocationDto getLastLocation(String carId) {
        CarLocation location = repository.findFirstByCar_IdOrderByCreatedAtDesc(carId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        return mapper.toDto(location);
    }

}
