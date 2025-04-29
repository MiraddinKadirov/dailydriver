package org.example.dailydriver.service;

import org.example.dailydriver.mapper.CarRatingMapper;
import org.example.dailydriver.model.dto.carratingDto.CarRatingDto;
import org.example.dailydriver.model.entity.AuthUser;
import org.example.dailydriver.model.entity.Car;
import org.example.dailydriver.model.entity.CarRating;
import org.example.dailydriver.repository.AuthUserRepository;
import org.example.dailydriver.repository.CarRatingRepository;
import org.example.dailydriver.repository.CarRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarRatingService {

    private final CarRepository carRepository;
    private final AuthUserRepository authUserRepository;
    private final CarRatingRepository carRatingRepository;
    private final CarRatingMapper carRatingMapper;

    public CarRatingService(CarRepository carRepository, AuthUserRepository authUserRepository, CarRatingRepository carRatingRepository, CarRatingMapper carRatingMapper) {
        this.carRepository = carRepository;
        this.authUserRepository = authUserRepository;
        this.carRatingRepository = carRatingRepository;
        this.carRatingMapper = carRatingMapper;
    }


    public void rateCar(CarRatingDto dto) {
        Car car = carRepository.findByIdAndNotDeleted(dto.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));
        AuthUser user = authUserRepository.findByIdAndNotDeleted(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean alreadyRated = carRatingRepository.findByCar_Id(car.getId()).stream()
                .anyMatch(r -> r.getUser().getId().equals(user.getId()));
        if (alreadyRated) {
            throw new RuntimeException("User already rated this car");
        }

        CarRating rating = carRatingMapper.toEntity(dto);
        rating.setCar(car); // MUHIM!
        rating.setUser(user); // MUHIM!

        carRatingRepository.save(rating);
        updateCarAverageRating(car.getId());
    }

    private void updateCarAverageRating(String carId) {
        List<CarRating> ratings = carRatingRepository.findByCar_Id(carId);
        double average = ratings.stream()
                .mapToInt(CarRating::getRating)
                .average()
                .orElse(0.0);

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        car.setRating(average);
        carRepository.save(car);
    }


}
