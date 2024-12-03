package com.example.hotel_booking.mapper;

import com.example.hotel_booking.dto.BookingRequestDTO;
import com.example.hotel_booking.dto.BookingResponseDTO;
import com.example.hotel_booking.model.Booking;
import com.example.hotel_booking.service.RoomService;
import com.example.hotel_booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BookingMapperDelegate implements BookingMapper{

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @Override
    public Booking requestToBooking(BookingRequestDTO bookingRequestDTO) {
        Booking booking = new Booking();
        booking.setCheckInDate(bookingRequestDTO.getCheckInDate());
        booking.setCheckOutDate(bookingRequestDTO.getCheckOutDate());
        booking.setRoom(roomService.findById(bookingRequestDTO.getRoom_id()));
        booking.setUser(userService.findById(bookingRequestDTO.getUser_id()));
        return booking;
    }

    @Override
    public Booking requestToBooking(Long bookingId, BookingRequestDTO bookingRequestDTO) {
        Booking booking = requestToBooking(bookingRequestDTO);
        booking.setId(bookingId);
        return booking;
    }

    @Override
    public BookingResponseDTO bookingToBookingResponse(Booking booking) {
        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();
        bookingResponseDTO.setId(booking.getId());
        bookingResponseDTO.setCheckInDate(booking.getCheckInDate());
        bookingResponseDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingResponseDTO.setRoom_id(booking.getRoom().getId());
        bookingResponseDTO.setUser_id(booking.getUser().getId());
        return bookingResponseDTO;
    }
}
