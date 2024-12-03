package com.example.hotel_booking.web.controller.statistics;

import com.example.hotel_booking.model.statistics.StatisticsBooking;
import com.example.hotel_booking.model.statistics.StatisticsCreateUser;
import com.example.hotel_booking.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/bookingSt")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public List<StatisticsBooking> getAllBooking() {
        return statisticsService.findAll();
    }

    @GetMapping("/usersSt")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public List<StatisticsCreateUser> getAllCreateUser() {
        return statisticsService.findAll2();
    }


    @GetMapping("/exportStatisticsCSV")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<byte[]> downloadCSV() {
        byte[] file = statisticsService.exportStatisticsToCSV();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=statisticsBooking.csv");
            return new ResponseEntity<>(file, headers, HttpStatus.OK);
    }
}
