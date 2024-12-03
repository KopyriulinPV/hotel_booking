package com.example.hotel_booking.service;

import com.example.hotel_booking.dto.HotelFilter;
import com.example.hotel_booking.model.Hotel;
import java.util.List;

public interface HotelService {

    List<Hotel> filterBy(HotelFilter hotelFilter);

    List<Hotel> findAll();

    Hotel findById(Long id);

    Hotel save(Hotel hotel);

    Hotel update(Hotel hotel);

    void deleteById(Long id);

    void updateRating(Long id, double newMark);
}
