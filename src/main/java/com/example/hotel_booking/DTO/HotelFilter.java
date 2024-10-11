package com.example.hotel_booking.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HotelFilter {

    Integer pageNumber;
    Integer pageSize;

    private Long id;
    private String hotelName;
    private String headline;
    private String city;
    private String address;
    private Double distanceFromCenter;
    private Double rating;
    private Integer ratingCount;

}
