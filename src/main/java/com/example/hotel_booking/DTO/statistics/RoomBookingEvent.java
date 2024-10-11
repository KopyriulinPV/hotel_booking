package com.example.hotel_booking.DTO.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookingEvent {

    private String userId;
    private String room_id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

}
