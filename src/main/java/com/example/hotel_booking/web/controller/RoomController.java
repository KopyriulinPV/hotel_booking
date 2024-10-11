package com.example.hotel_booking.web.controller;

import com.example.hotel_booking.DTO.*;
import com.example.hotel_booking.mapper.RoomMapper;
import com.example.hotel_booking.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    private final RoomMapper roomMapper;

    @GetMapping("/roomsFilter")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<RoomListResponseDTO> filterBy(RoomFilter roomFilter) {
        return ResponseEntity.ok(roomMapper.roomListToRoomListResponse(roomService.filterBy(roomFilter)));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<RoomListResponseDTO> getAllRooms() {
        return ResponseEntity.ok(roomMapper.roomListToRoomListResponse(roomService.findAll()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<RoomResponseDTO> getRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(roomMapper.roomToRoomResponse(roomService.findById(id)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RoomResponseDTO> createRoom(@RequestBody RoomRequestDTO roomRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roomMapper.roomToRoomResponse(
                        roomService.save(roomMapper.requestToRoom(roomRequestDTO))));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RoomResponseDTO> updateRoom(@PathVariable Long id, @RequestBody RoomRequestDTO roomRequestDTO) {
        return ResponseEntity.ok(roomMapper.roomToRoomResponse(
                roomService.update(roomMapper.requestToRoom(id, roomRequestDTO))));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
