package com.example.hotel_booking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RoomFilter {

    Integer pageNumber;
    Integer pageSize;

    private Long id;

    private String roomNumber;

    private Double minPrice;

    private Double maxPrice;

    private Integer maxOccupancy;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long id_hotel;

}
