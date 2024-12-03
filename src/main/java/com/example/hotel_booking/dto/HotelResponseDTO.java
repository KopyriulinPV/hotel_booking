package com.example.hotel_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponseDTO {

    private Long id;
    private String hotelName;
    private String headline;
    private String city;
    private String address;
    private double distanceFromCenter;
    private double rating;
    private int ratingCount;

}
