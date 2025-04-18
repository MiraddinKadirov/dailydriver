package org.example.dailydriver.service;

import org.example.dailydriver.mapper.CarMapper;
import org.example.dailydriver.model.dto.carDto.CarCreateDto;
import org.example.dailydriver.model.dto.carDto.CarDto;
import org.example.dailydriver.model.dto.carDto.CarUpdateDto;
import org.example.dailydriver.model.dto.commentDto.CommentDto;
import org.example.dailydriver.model.entity.Car;
import org.example.dailydriver.model.enums.Category;
import org.example.dailydriver.repository.CarLocationRepository;
import org.example.dailydriver.repository.CarRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService implements CrudService<CarCreateDto, CarUpdateDto, CarDto, String> {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final CommentService commentService;
    private final CarLocationRepository carLocationRepository;

    public CarService(CarRepository carRepository, CarMapper carMapper, CommentService commentService, CarLocationRepository carLocationRepository) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
        this.commentService = commentService;
        this.carLocationRepository = carLocationRepository;
    }

    @Override
    public CarDto save(CarCreateDto entity) {
        Car car = carMapper.toEntity(entity);
        Car save = carRepository.save(car);
        return carMapper.toDto(save);
    }

    @Override
    public CarDto update(CarUpdateDto entity, String id) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        Optional<Car> byIdAndNotDeleted = carRepository.findByIdAndNotDeleted(id);
        if (byIdAndNotDeleted.isPresent()) {
            Car car = byIdAndNotDeleted.get();
            car.setDeleted(true);
            carRepository.save(car);
            return true;
        }
        return false;
    }

    @Override
    public CarDto findById(String id) {
        Car car = carRepository.findByIdAndNotDeleted(id).orElseThrow(() -> new RuntimeException("Car not found"));
        return carMapper.toDto(car);
    }

    @Override
    public List<CarDto> findAll() {

        List<Car> cars = carRepository.findPopularCars();
        List<CarDto> carDtoList = carMapper.toDtoList(cars);

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            car.setLocations(carLocationRepository.findAllByCar_IdOrderByCreatedAtDesc(car.getId()));
            CarDto dto = carDtoList.get(i);
            List<CommentDto> comments = commentService.findAll(car.getId());
            dto.setComments(comments);
        }

        return carDtoList;    }

    public void setCarActiveStatus(String carId, boolean active) {
        boolean exists = carRepository.existsById(carId);
        if (!exists) {
            throw new RuntimeException("Car not found");
        }
        carRepository.updateCarActiveStatus(carId, active);
    }

    public List<CarDto> getCarsByRating(Double rating) {
        if (rating < 1 || rating > 5)
            throw new IllegalArgumentException("Rating faqat 1 dan 5 gacha bo‘lishi kerak");

        List<Car> cars = carRepository.findByRating(rating);
        return carMapper.toDtoList(cars);
    }

    public Page<CarDto> findAllPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Car> carPage = carRepository.findAllActiveUnavailableNotDeleted(pageable);

        return carPage.map(car -> {
            CarDto dto = carMapper.toDto(car);
            List<CommentDto> commentDtoList = commentService.findAll(car.getId());
            dto.setComments(commentDtoList);
            return dto;
        });
    }

    public Long getTotalCars() {
        return carRepository.countAllByNotDeleted();
    }

    public Long getUnavailableCars() {
        return carRepository.countAllByNotAvailable();
    }

    public Long getActiveCars() {
        return carRepository.countAllByActive();
    }

    public List<CarDto> getCarsByCategory(Category category) {
        return carRepository.findAllByCategoryAndDeletedFalse(category)
                .stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }

    public Double getAverageRating() {
        return carRepository.getAverageRating();
    }

    public Map<String, Long> getCarCountByCategory() {
        List<Object[]> raw = carRepository.countByCategory();
        return raw.stream()
                .collect(Collectors.toMap(
                        r -> ((Category) r[0]).name(),
                        r -> (Long) r[1]
                ));
    }

    public Map<Integer, Long> getCarCountByProductionYear() {
        List<Object[]> raw = carRepository.countByProductionYear();
        return raw.stream()
                .collect(Collectors.toMap(
                        r -> (Integer) r[0],
                        r -> (Long) r[1]
                ));
    }

}
