package org.example.dailydriver.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.dailydriver.model.dto.carDto.CarCreateDto;
import org.example.dailydriver.model.dto.carDto.CarDto;
import org.example.dailydriver.model.dto.carDto.CarUpdateDto;
import org.example.dailydriver.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminCarController {


    private final CarService carService;

    public AdminCarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable String id) {
        return ResponseEntity.ok(carService.findById(id));
    }

    @Operation(summary = "Mashinaning active holatini o‘zgartirish", description = "Admin tomonidan mashinani active=true/false qilish")
    @PatchMapping("/{id}/active")
    public ResponseEntity<Void> setCarActiveStatus(
            @PathVariable String id,
            @RequestParam boolean active
    ) {
        carService.setCarActiveStatus(id, active);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/count")
    public ResponseEntity<Long> getTotalCars() {
        return ResponseEntity.ok(carService.getTotalCars());
    }

    @Operation(summary = "Band mashinalar soni", description = "available=false bo‘lgan mashinalar sonini qaytaradi")
    @GetMapping("/count/unavailable")
    public ResponseEntity<Long> getUnavailableCars() {
        return ResponseEntity.ok(carService.getUnavailableCars());
    }

    @GetMapping("/count/active")
    public ResponseEntity<Long> getActiveCars() {
        return ResponseEntity.ok(carService.getActiveCars());
    }

    @GetMapping("/count/by-category")
    public ResponseEntity<Map<String, Long>> getCarCountByCategory() {
        return ResponseEntity.ok(carService.getCarCountByCategory());
    }

    @Operation(summary = "Yangi mashina qo‘shish", description = "Yangi mashina yaratadi")
    @PostMapping
    public ResponseEntity<CarDto> createCar(@RequestBody CarCreateDto car) {
        return ResponseEntity.ok(carService.save(car));
    }

    @Operation(summary = "Mashina o‘chirish", description = "Berilgan ID orqali mashinani o‘chiradi")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCar(@PathVariable String id) {
        return ResponseEntity.ok(carService.delete(id));
    }

    @Operation(summary = "Mashinani yangilash", description = "Berilgan ID bo‘yicha mashinani yangilaydi")
    @PatchMapping("/{id}")
    public ResponseEntity<CarDto> updateCar(@RequestBody CarUpdateDto car,
                                            @PathVariable String id) {
        return ResponseEntity.ok(carService.update(car, id));
    }


    @GetMapping("/count/by-year")
    public ResponseEntity<Map<Integer, Long>> getCarCountByProductionYear() {
        return ResponseEntity.ok(carService.getCarCountByProductionYear());
    }
}
