package org.example.dailydriver.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.dailydriver.model.dto.carbookingDto.CarBookingDto;
import org.example.dailydriver.model.entity.CarBooking;
import org.example.dailydriver.service.CarBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/booking")
public class CarBookingController {


    private final CarBookingService bookingService;

    public CarBookingController(CarBookingService bookingService) {
        this.bookingService = bookingService;
    }


    @Operation(summary = "Mashinani band qilish", description = "Berilgan vaqt oralig‘ida mashinani band qiladi")
    @PostMapping
    public ResponseEntity<CarBooking> bookCar(@RequestBody CarBookingDto dto) {
        return ResponseEntity.ok(bookingService.bookCar(dto));
    }

}
