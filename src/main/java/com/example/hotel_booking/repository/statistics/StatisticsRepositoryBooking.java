package com.example.hotel_booking.repository.statistics;

import com.example.hotel_booking.model.statistics.StatisticsBooking;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface StatisticsRepositoryBooking extends MongoRepository<StatisticsBooking, String> {



}
