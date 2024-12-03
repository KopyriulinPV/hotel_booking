package com.example.hotel_booking.web.controller;

import com.example.hotel_booking.dto.HotelFilter;
import com.example.hotel_booking.dto.HotelListResponseDTO;
import com.example.hotel_booking.dto.HotelRequestDTO;
import com.example.hotel_booking.dto.HotelResponseDTO;
import com.example.hotel_booking.mapper.HotelMapper;
import com.example.hotel_booking.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    private final HotelMapper hotelMapper;

    @GetMapping("/hotelsFilter")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public HotelListResponseDTO filterBy(HotelFilter hotelFilter) {
        return hotelMapper.hotelListToHotelListResponse(hotelService.filterBy(hotelFilter));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public HotelListResponseDTO getAllHotels() {
        return hotelMapper.hotelListToHotelListResponse(hotelService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public HotelResponseDTO getHotelById(@PathVariable Long id) {
        return hotelMapper.hotelToHotelResponse(hotelService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public HotelResponseDTO createHotel(@RequestBody HotelRequestDTO hotelRequestDTO) {
        return hotelMapper.hotelToHotelResponse(
                hotelService.save(hotelMapper.requestToHotel(hotelRequestDTO)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public HotelResponseDTO updateHotel(@PathVariable Long id, @RequestBody HotelRequestDTO hotelRequestDTO) {
        return hotelMapper.hotelToHotelResponse(
                hotelService.update(hotelMapper.requestToHotel(id, hotelRequestDTO)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteHotel(@PathVariable Long id) {
        hotelService.deleteById(id);
    }

    @PutMapping("/{id}/rating")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Void> updateRating(@PathVariable Long id, @RequestParam double newMark) {
        if (newMark < 1 || newMark > 5) {
            return ResponseEntity.badRequest().build();
        }
        hotelService.updateRating(id, newMark);
        return ResponseEntity.ok().build();
    }

}
