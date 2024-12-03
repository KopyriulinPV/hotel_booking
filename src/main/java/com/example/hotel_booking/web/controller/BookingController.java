package com.example.hotel_booking.web.controller;

import com.example.hotel_booking.dto.BookingListResponseDTO;
import com.example.hotel_booking.dto.BookingRequestDTO;
import com.example.hotel_booking.dto.BookingResponseDTO;
import com.example.hotel_booking.mapper.BookingMapper;
import com.example.hotel_booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    private final BookingMapper bookingMapper;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BookingListResponseDTO getAllBooking() {
        return bookingMapper.bookingListToBookingListResponse(bookingService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BookingResponseDTO getBookingById(@PathVariable Long id) {
        return bookingMapper.bookingToBookingResponse(bookingService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public BookingResponseDTO createBooking(@RequestBody BookingRequestDTO bookingRequestDTO) {
        return bookingMapper.bookingToBookingResponse(
                        bookingService.save(bookingMapper.requestToBooking(bookingRequestDTO)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public BookingResponseDTO updateBooking(@PathVariable Long id, @RequestBody BookingRequestDTO bookingRequestDTO) {
        return bookingMapper.bookingToBookingResponse(
                bookingService.update(bookingMapper.requestToBooking(id, bookingRequestDTO)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteById(id);
    }

}
