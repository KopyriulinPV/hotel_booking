package com.example.hotel_booking.service.impl;

import com.example.hotel_booking.DTO.statistics.RoomBookingEvent;
import com.example.hotel_booking.mapper.statistics.StatisticsMapper;
import com.example.hotel_booking.model.Booking;
import com.example.hotel_booking.repository.BookingRepository;
import com.example.hotel_booking.repository.RoomRepository;
import com.example.hotel_booking.service.BookingService;
import com.example.hotel_booking.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final RoomRepository roomRepository;

    private final StatisticsMapper statisticsMapper;

    @Value("${app.kafka.kafkaMessageTopic1}")
    private String topicName;

    private final KafkaTemplate<String, RoomBookingEvent> kafkaTemplate;

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findById(Long id) {
        return bookingRepository.findById(id).get();
    }

    public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = new ArrayList<>();

        long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate);

        for (int i = 0; i <= numberOfDays; i++) {
            dates.add(startDate.plusDays(i));
        }

        return dates;
    }

    public boolean checkRoomAvailability(Long room_Id, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Booking> existingBookings = bookingRepository.findByRoom_IdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(room_Id, checkInDate, checkInDate);

        List<LocalDate> unavailableDates = roomRepository.findById(room_Id).get().getUnavailableDates();
        List<LocalDate> tt = getDatesBetween(checkInDate, checkOutDate);
        for (LocalDate rr : tt) {
            for (LocalDate qq : unavailableDates) {
                if (rr.equals(qq)) {
                    return false;
                }
            }
        }
        return existingBookings.isEmpty();
    }



    @Override
    public Booking save(Booking booking) {
        if (checkRoomAvailability(booking.getRoom().getId(), booking.getCheckInDate(), booking.getCheckOutDate())) {
            Booking booking1 = bookingRepository.save(booking);
            kafkaTemplate.send(topicName, statisticsMapper.bookingToRoomBookingEvent(booking1));
            return booking1;
        }
        return null;
    }

    @Override
    public Booking update(Booking booking) {
        Booking existedBooking = bookingRepository.findById(booking.getId()).get();
        BeanUtils.copyNonNullProperties(booking, existedBooking);
        return bookingRepository.save(existedBooking);
    }

    @Override
    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }
}
