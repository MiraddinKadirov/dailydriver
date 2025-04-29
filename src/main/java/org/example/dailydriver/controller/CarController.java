package org.example.dailydriver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.dailydriver.model.dto.carDto.CarCreateDto;
import org.example.dailydriver.model.dto.carDto.CarDto;
import org.example.dailydriver.model.dto.carDto.CarUpdateDto;
import org.example.dailydriver.model.enums.Category;
import org.example.dailydriver.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Car Controller", description = "Mashinalar bilan ishlovchi endpointlar")
@RestController
@RequestMapping("/api/v1/car")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Operation(
            summary = "Eng mashhur mashinalarni olish",
            description = "Reytingi yuqori va komentlari ko‘p bo‘lgan mashinalarni tartiblangan ro‘yxatda qaytaradi"
    )
    @GetMapping("/popular")
    public ResponseEntity<List<CarDto>> getPopularCars() {
        return ResponseEntity.ok(carService.findAll());
    }


    @Operation(
            summary = "Reyting bo‘yicha mashinalarni olish",
            description = "Foydalanuvchi 1 dan 5 gacha rating yuboradi, shunga mos mashinalar ro‘yxati qaytariladi"
    )
    @GetMapping("/rating{number}")
    public ResponseEntity<List<CarDto>> getCarsByRating(
            @Parameter(description = "Rating qiymati (1 dan 5 gacha)")
            @PathVariable Double number) {
        return ResponseEntity.ok(carService.getCarsByRating(number));
    }


    @Operation(summary = "Bitta mashinani olish", description = "Mashinaning ID si orqali topib beradi")
    @GetMapping("/carget{id}")
    public ResponseEntity<CarDto> getCar(@PathVariable String id) {
        return ResponseEntity.ok(carService.findById(id));
    }

    @Operation(summary = "Mashinalarni paginate qilib olish", description = "Mashinalarni sahifalab (page, size) bo‘yicha olib beradi")
    @GetMapping("/getpage")
    public ResponseEntity<Page<CarDto>> getPagedCars(
            @Parameter(description = "Sahifa raqami (0 dan boshlanadi)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Sahifadagi elementlar soni") @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(carService.findAllPaged(page, size));
    }

    @Operation(summary = "Kategoriya bo‘yicha mashinalar", description = "Berilgan kategoriya bo‘yicha mashinalarni qaytaradi")
    @GetMapping("/category/{category}")
    public ResponseEntity<List<CarDto>> getCarsByCategory(@PathVariable Category category) {
        return ResponseEntity.ok(carService.getCarsByCategory(category));
    }

    @PreAuthorize("hasAnyRole('SUPER_USER', 'ADMIN')")
    @Operation(summary = " >>ADMIN PANEL UCHUN<< Mashinaning active holatini o‘zgartirish", description = "Admin tomonidan mashinani active=true/false qilish")
    @PatchMapping("/active-status/{id}")
    public ResponseEntity<Void> setCarActiveStatus(
            @PathVariable String id,
            @RequestParam boolean active
    ) {
        carService.setCarActiveStatus(id, active);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('SUPER_USER', 'ADMIN')")
    @GetMapping("/count-car")
    public ResponseEntity<Long> getTotalCars() {
        return ResponseEntity.ok(carService.getTotalCars());
    }

    @PreAuthorize("hasAnyRole('SUPER_USER', 'ADMIN')")
    @Operation(summary = " >>ADMIN PANEL UCHUN<< Band mashinalar soni", description = "available=false bo‘lgan mashinalar sonini qaytaradi")
    @GetMapping("/count/unavailable")
    public ResponseEntity<Long> getUnavailableCars() {
        return ResponseEntity.ok(carService.getUnavailableCars());
    }

    @PreAuthorize("hasAnyRole('SUPER_USER', 'ADMIN')")
    @GetMapping("/count-active-car")
    public ResponseEntity<Long> getActiveCars() {
        return ResponseEntity.ok(carService.getActiveCars());
    }

    @PreAuthorize("hasAnyRole('SUPER_USER', 'ADMIN')")
    @Operation(summary = ">>ADMIN PANEL UCHUN<< Categoryalr boyicha mashinalar soni")
    @GetMapping("/count/by-category")
    public ResponseEntity<Map<String, Long>> getCarCountByCategory() {
        return ResponseEntity.ok(carService.getCarCountByCategory());
    }

    @PreAuthorize("hasAnyRole('SUPER_USER', 'ADMIN')")
    @Operation(summary = " >>ADMIN PANEL UCHUN<< Yangi mashina qo‘shish", description = "Yangi mashina yaratadi")
    @PostMapping("/car-create")
    public ResponseEntity<CarDto> createCar(@RequestBody CarCreateDto car) {
        return ResponseEntity.ok(carService.save(car));
    }

    @PreAuthorize("hasAnyRole('SUPER_USER', 'ADMIN')")
    @Operation(summary = " >>ADMIN PANEL UCHUN<< Mashina o‘chirish", description = "Berilgan ID orqali mashinani o‘chiradi")
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCar(@PathVariable String id) {
        return ResponseEntity.ok(carService.delete(id));
    }

    @PreAuthorize("hasAnyRole('SUPER_USER', 'ADMIN')")
    @Operation(summary = " >>ADMIN PANEL UCHUN<< Mashinani yangilash", description = "Berilgan ID bo‘yicha mashinani yangilaydi")
    @PatchMapping("/{id}")
    public ResponseEntity<CarDto> updateCar(@RequestBody CarUpdateDto car,
                                            @PathVariable String id) {
        return ResponseEntity.ok(carService.update(car, id));
    }

    @PreAuthorize("hasAnyRole('SUPER_USER', 'ADMIN')")
    @Operation(summary = " >>ADMIN PANEL UCHUN<< Mashinani yili boicha soni")
    @GetMapping("/count/by-year")
    public ResponseEntity<Map<Integer, Long>> getCarCountByProductionYear() {
        return ResponseEntity.ok(carService.getCarCountByProductionYear());
    }

}
