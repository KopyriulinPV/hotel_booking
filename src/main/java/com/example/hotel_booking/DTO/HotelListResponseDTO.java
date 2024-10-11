package com.example.hotel_booking.DTO;

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
