package com.example.hotel_booking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelRequestDTO {

    private String hotelName;;
    private String headline;
    private String city;
    private String address;
    private double distanceFromCenter;

}
