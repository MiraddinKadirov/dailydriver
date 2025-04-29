package org.example.dailydriver.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.dailydriver.model.dto.carratingDto.CarRatingDto;
import org.example.dailydriver.service.CarRatingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/rating")
public class CarRatingController {

    private final CarRatingService service;

    @Operation(summary = " Car Reyting baxo berish 1 dan 5 gacha ")
    @PostMapping("/rating")
    public void rateCar(@RequestBody CarRatingDto car) {
        service.rateCar(car);
    }

}
