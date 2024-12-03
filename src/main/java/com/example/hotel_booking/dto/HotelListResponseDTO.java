package com.example.hotel_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelListResponseDTO {

    private List<HotelResponseDTO> hotelResponses = new ArrayList<>();

}
