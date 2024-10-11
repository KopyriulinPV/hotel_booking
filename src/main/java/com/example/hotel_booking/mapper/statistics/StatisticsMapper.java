package com.example.hotel_booking.mapper.statistics;

import com.example.hotel_booking.DTO.statistics.CreateUserEvent;
import com.example.hotel_booking.DTO.statistics.RoomBookingEvent;
import com.example.hotel_booking.model.Booking;
import com.example.hotel_booking.model.User;
import com.example.hotel_booking.model.statistics.StatisticsBooking;
import com.example.hotel_booking.model.statistics.StatisticsCreateUser;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@DecoratedWith(BookingEventMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StatisticsMapper {

    RoomBookingEvent bookingToRoomBookingEvent(Booking booking);

    StatisticsBooking roomBookingEventToStatistics(RoomBookingEvent roomBookingEvent);

    CreateUserEvent userToCreateUserEvent(User user);

    StatisticsCreateUser createUserEventToStatisticsCreateUser(CreateUserEvent createUserEvent);

}
