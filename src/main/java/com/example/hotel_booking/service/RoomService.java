package com.example.hotel_booking.service;

import com.example.hotel_booking.DTO.RoomFilter;
import com.example.hotel_booking.model.Room;
import java.util.List;

public interface RoomService {

    List<Room> filterBy(RoomFilter roomFilter);

    List<Room> findAll();

    Room findById(Long id);

    Room save(Room room);

    Room update(Room room);

    void deleteById(Long id);

}
