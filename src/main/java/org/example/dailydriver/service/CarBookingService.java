package org.example.dailydriver.service;

import org.example.dailydriver.model.dto.carbookingDto.CarBookingDto;
import org.example.dailydriver.model.entity.Car;
import org.example.dailydriver.model.entity.CarBooking;
import org.example.dailydriver.repository.CarBookingRepository;
import org.example.dailydriver.repository.CarRepository;
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


    public CarBooking bookCar(CarBookingDto dto) {
        Car car = carRepository.findById(dto.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));
        List<CarBooking> activeBookings = bookingRepository.findActiveBookingsByCarId(dto.getCarId());
        if (!activeBookings.isEmpty()) {
            throw new IllegalStateException("Car is already booked during this period");
        }

        CarBooking booking = new CarBooking();
        booking.setCar(car);
        booking.setStartTime(dto.getStartTime());
        booking.setEndTime(dto.getEndTime());
        booking.setActive(true);

        return bookingRepository.save(booking);
    }


    public void releaseExpiredBookings() {
        List<CarBooking> all = bookingRepository.findAll();
        LocalDateTime now = LocalDateTime.now();
        for (CarBooking booking : all) {
            if (booking.getEndTime().isBefore(now) && booking.isActive()) {
                booking.setActive(false);
                bookingRepository.save(booking);
            }
        }
    }
}
