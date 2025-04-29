package org.example.dailydriver.service;

import org.example.dailydriver.model.dto.carbookingDto.CarBookingDto;
import org.example.dailydriver.model.entity.Car;
import org.example.dailydriver.model.entity.CarBooking;
import org.example.dailydriver.repository.CarBookingRepository;
import org.example.dailydriver.repository.CarRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CarBookingService {


    private final CarRepository carRepository;
    private final CarBookingRepository bookingRepository;

    public CarBookingService(CarRepository carRepository, CarBookingRepository bookingRepository) {
        this.carRepository = carRepository;
        this.bookingRepository = bookingRepository;
    }

    @Async
    public void bookCar(CarBookingDto dto) {
        Car car = carRepository.findById(dto.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        List<CarBooking> activeBookings = bookingRepository.findActiveBookingsByCarId(dto.getCarId());
        if (!activeBookings.isEmpty()) {
            throw new IllegalStateException("Car is already booked during this period");
        }

        car.setIsAvailable(true);
        carRepository.save(car);

        CarBooking booking = new CarBooking();
        booking.setCar(car);
        booking.setStartTime(dto.getStartTime());
        booking.setEndTime(dto.getEndTime());
        booking.setActive(true);

         bookingRepository.save(booking);
    }


    @Async
    public void releaseExpiredBookings() {

        LocalDateTime now = LocalDateTime.now();
        List<CarBooking> expiredBookings = bookingRepository.findAll().stream()
                .filter(booking -> booking.getEndTime().isBefore(now) && booking.isActive())
                .toList();
        for (CarBooking booking : expiredBookings) {
            booking.setActive(true);
            bookingRepository.save(booking);
            Car car = booking.getCar();
            car.setIsAvailable(false);
            carRepository.save(car);
        }
    }

}
