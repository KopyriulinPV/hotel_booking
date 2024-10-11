package com.example.hotel_booking.repository.statistics;

import com.example.hotel_booking.model.statistics.StatisticsCreateUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatisticsRepositoryCrUser extends MongoRepository<StatisticsCreateUser, String> {
}
