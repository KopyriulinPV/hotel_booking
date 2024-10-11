package com.example.hotel_booking.model.statistics;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "statistics_create_user")
public class StatisticsCreateUser {
    @Id
    private String id;
    private String userId;

}
