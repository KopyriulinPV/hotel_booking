package com.example.hotel_booking.service.statistics;

import com.example.hotel_booking.model.statistics.StatisticsBooking;
import com.example.hotel_booking.model.statistics.StatisticsCreateUser;
import com.example.hotel_booking.repository.statistics.StatisticsRepositoryBooking;
import com.example.hotel_booking.repository.statistics.StatisticsRepositoryCrUser;
import com.example.hotel_booking.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepositoryBooking statisticsRepositoryBooking;

    private final StatisticsRepositoryCrUser statisticsRepositoryCrUser;

    @Override
    public List<StatisticsBooking> findAll() {
        return statisticsRepositoryBooking.findAll();
    }

    @Override
    public List<StatisticsCreateUser> findAll2() {
        return statisticsRepositoryCrUser.findAll();
    }


    public byte[] exportStatisticsToCSV() {
        StringBuilder csvBuilder = new StringBuilder();

        List<StatisticsCreateUser> statistics = statisticsRepositoryCrUser.findAll();
        for (StatisticsCreateUser statistic : statistics) {
            csvBuilder.append("createUser: ").append(statistic.getUserId()).append("\n");
        }

        List<StatisticsBooking> statistics2 = statisticsRepositoryBooking.findAll();
        for (StatisticsBooking statistic : statistics2) {
            csvBuilder.append("userId: ").append(statistic.getUserId()).append("; ")
                    .append("room_id: ").append(statistic.getRoom_id()).append("; ")
                    .append("checkInDate: ").append(statistic.getCheckInDate()).append("; ")
                    .append("checkOutDate: ").append(statistic.getCheckOutDate()).append(".\n");
        }
        byte[] content = csvBuilder.toString().getBytes(StandardCharsets.UTF_8);
        return content;
    }

}
