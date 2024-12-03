package com.example.hotel_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequestDTO {

    private String name;
    private String description;
    private String roomNumber;
    private double price;
    private int maxOccupancy;
    private List<LocalDate> unavailableDates;
    private Long hotel_id;

}
