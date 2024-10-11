package com.example.hotel_booking.web.controller;

import com.example.hotel_booking.DTO.BookingListResponseDTO;
import com.example.hotel_booking.DTO.BookingRequestDTO;
import com.example.hotel_booking.DTO.BookingResponseDTO;
import com.example.hotel_booking.mapper.BookingMapper;
import com.example.hotel_booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BookingListResponseDTO> getAllBooking() {
        return ResponseEntity.ok(bookingMapper.bookingListToBookingListResponse(bookingService.findAll()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingMapper.bookingToBookingResponse(bookingService.findById(id)));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody BookingRequestDTO bookingRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookingMapper.bookingToBookingResponse(
                        bookingService.save(bookingMapper.requestToBooking(bookingRequestDTO))));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<BookingResponseDTO> updateBooking(@PathVariable Long id, @RequestBody BookingRequestDTO bookingRequestDTO) {
        return ResponseEntity.ok(bookingMapper.bookingToBookingResponse(
                bookingService.update(bookingMapper.requestToBooking(id, bookingRequestDTO))));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
