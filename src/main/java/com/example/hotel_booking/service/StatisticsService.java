package com.example.hotel_booking.service;

import com.example.hotel_booking.model.statistics.StatisticsBooking;
import com.example.hotel_booking.model.statistics.StatisticsCreateUser;
import java.util.List;

public interface StatisticsService {

    List<StatisticsBooking> findAll();

    List<StatisticsCreateUser> findAll2();

    byte[] exportStatisticsToCSV();
}
