package org.example.dailydriver.controller;

import org.example.dailydriver.model.dto.locationDto.CarLocationDto;
import org.example.dailydriver.model.dto.locationDto.LocationCreateDto;
import org.example.dailydriver.service.CarLocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/location")
public class CarLocationController {

      private final CarLocationService carLocationService;

    public CarLocationController(CarLocationService carLocationService) {
        this.carLocationService = carLocationService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<CarLocationDto> addLocation(@RequestBody LocationCreateDto dto,
                                                      @PathVariable String id) {
        return ResponseEntity.ok(carLocationService.save(dto, id));
    }

    @GetMapping("/history/{carId}")
    public ResponseEntity<List<CarLocationDto>> getLocationHistory(@PathVariable String carId) {
        return ResponseEntity.ok(carLocationService.getLocationHistory(carId));
    }

    @GetMapping("/last/{carId}")
    public ResponseEntity<CarLocationDto> getLastLocation(@PathVariable String carId) {
        return ResponseEntity.ok(carLocationService.getLastLocation(carId));
    }

}
