package com.example.hotel_booking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long room_id;

    private Long user_id;

}
