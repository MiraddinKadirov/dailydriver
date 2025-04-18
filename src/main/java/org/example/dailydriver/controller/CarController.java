package org.example.dailydriver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.dailydriver.model.dto.carDto.CarDto;
import org.example.dailydriver.model.enums.Category;
import org.example.dailydriver.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Car Controller", description = "Mashinalar bilan ishlovchi endpointlar")
@RestController
@RequestMapping("/car")
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
    @GetMapping("/rating")
    public ResponseEntity<List<CarDto>> getCarsByRating(
            @Parameter(description = "Rating qiymati (1 dan 5 gacha)")
            @RequestParam Double rating) {
        return ResponseEntity.ok(carService.getCarsByRating(rating));
    }


    @Operation(summary = "Bitta mashinani olish", description = "Mashinaning ID si orqali topib beradi")
    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCar(@PathVariable String id) {
        return ResponseEntity.ok(carService.findById(id));
    }

    @Operation(summary = "Mashinalarni paginate qilib olish", description = "Mashinalarni sahifalab (page, size) bo‘yicha olib beradi")
    @GetMapping
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

}
