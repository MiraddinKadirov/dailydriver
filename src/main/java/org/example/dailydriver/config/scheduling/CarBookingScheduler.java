package org.example.dailydriver.config.scheduling;

import org.example.dailydriver.service.CarBookingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CarBookingScheduler {

    private final CarBookingService bookingService;

    public CarBookingScheduler(CarBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Scheduled(cron = "0 * * * * *")
    public void releaseExpiredBookings() {
        bookingService.releaseExpiredBookings();
    }

}
