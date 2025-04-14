package org.example.dailydriver.controller;

import org.example.dailydriver.model.dto.carDto.CarCreateDto;
import org.example.dailydriver.model.dto.carDto.CarDto;
import org.example.dailydriver.model.dto.carDto.CarUpdateDto;
import org.example.dailydriver.service.CarService;
import org.example.dailydriver.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/car")
public class CarController {

    private final CarService carService;
    private final FileService fileService;

    public CarController(CarService carService, FileService fileService) {
        this.carService = carService;
        this.fileService = fileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCar(@PathVariable String id) {
        return ResponseEntity.ok(carService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> findAll() {
        return ResponseEntity.status(200).body(carService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<CarDto> createCar(@RequestBody CarCreateDto car) {
        CarDto save = carService.save(car);
        return ResponseEntity.status(200).body(save);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCar(@PathVariable String id) {
        return ResponseEntity.ok(carService.delete(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CarDto> updateCar(@RequestBody CarUpdateDto car,
                                            @PathVariable String id) {
        return ResponseEntity.ok(carService.update(car, id));
    }



}
