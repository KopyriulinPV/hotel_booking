package com.example.hotel_booking.mapper;

import com.example.hotel_booking.DTO.*;
import com.example.hotel_booking.model.Booking;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(BookingMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {

    Booking requestToBooking(BookingRequestDTO bookingRequestDTO);

    @Mapping(source = "bookingId", target = "id")
    Booking requestToBooking(Long bookingId, BookingRequestDTO bookingRequestDTO);

    BookingResponseDTO bookingToBookingResponse(Booking booking);

    default BookingListResponseDTO bookingListToBookingListResponse(List<Booking> booking) {
        BookingListResponseDTO bookingListResponseDTO = new BookingListResponseDTO();
        bookingListResponseDTO.setBookingResponses(booking.stream()
                .map(this::bookingToBookingResponse).collect(Collectors.toList()));
        return bookingListResponseDTO;
    }
}
