package com.example.hotel_booking.model.statistics;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "statistics_booking")
public class StatisticsBooking {

    @Id
    private String id;
    private String userId;
    private String room_id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

}
