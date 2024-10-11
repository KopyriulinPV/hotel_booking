package com.example.hotel_booking.service;

import com.example.hotel_booking.model.Booking;
import java.util.List;

public interface BookingService {

    List<Booking> findAll();

    Booking findById(Long id);

    Booking save(Booking booking);

    Booking update(Booking booking);

    void deleteById(Long id);

}
