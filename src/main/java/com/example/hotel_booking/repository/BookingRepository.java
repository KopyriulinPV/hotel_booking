package com.example.hotel_booking.repository;

import com.example.hotel_booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByRoom_IdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
            Long room_Id, LocalDate checkInDate, LocalDate checkOutDate);
}
