package com.example.hotel_booking.mapper.statistics;

import com.example.hotel_booking.dto.statistics.CreateUserEvent;
import com.example.hotel_booking.dto.statistics.RoomBookingEvent;
import com.example.hotel_booking.model.Booking;
import com.example.hotel_booking.model.User;

public abstract class BookingEventMapperDelegate implements StatisticsMapper {


    public RoomBookingEvent bookingToRoomBookingEvent(Booking booking) {
        RoomBookingEvent roomBookingEvent = new RoomBookingEvent();
        roomBookingEvent.setUserId(booking.getUser().getId().toString());
        roomBookingEvent.setRoom_id(booking.getRoom().getId().toString());
        roomBookingEvent.setCheckInDate(booking.getCheckInDate());
        roomBookingEvent.setCheckOutDate(booking.getCheckOutDate());
        return roomBookingEvent;
    }

    public CreateUserEvent userToCreateUserEvent(User user) {
        CreateUserEvent createUserEvent = new CreateUserEvent();
        createUserEvent.setUserId(user.getId().toString());
        return createUserEvent;
    }

}
