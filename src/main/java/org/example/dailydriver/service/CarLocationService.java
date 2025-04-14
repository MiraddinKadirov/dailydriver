package org.example.dailydriver.service;

import org.example.dailydriver.model.entity.CarLocation;
import org.example.dailydriver.repository.CarLocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarLocationService implements CrudService<CarLocation, CarLocation, CarLocation, String> {

    private final CarLocationRepository repository;

    public CarLocationService(CarLocationRepository repository) {
        this.repository = repository;
    }

    @Override
    public CarLocation save(CarLocation entity) {
        return null;
    }

    @Override
    public CarLocation update(CarLocation entity, String id) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }

    @Override
    public CarLocation findById(String id) {
        return null;
    }

    @Override
    public List<CarLocation> findAll() {
        return List.of();
    }
}
