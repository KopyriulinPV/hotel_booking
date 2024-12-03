package com.example.hotel_booking.web.controller;

import com.example.hotel_booking.dto.*;
import com.example.hotel_booking.mapper.RoomMapper;
import com.example.hotel_booking.service.RoomService;
import lombok.RequiredArgsConstructor;
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
    public RoomListResponseDTO filterBy(RoomFilter roomFilter) {
        return roomMapper.roomListToRoomListResponse(roomService.filterBy(roomFilter));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public RoomListResponseDTO getAllRooms() {
        return roomMapper.roomListToRoomListResponse(roomService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public RoomResponseDTO getRoomById(@PathVariable Long id) {
        return roomMapper.roomToRoomResponse(roomService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RoomResponseDTO createRoom(@RequestBody RoomRequestDTO roomRequestDTO) {
        return roomMapper.roomToRoomResponse(
                        roomService.save(roomMapper.requestToRoom(roomRequestDTO)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RoomResponseDTO updateRoom(@PathVariable Long id, @RequestBody RoomRequestDTO roomRequestDTO) {
        return roomMapper.roomToRoomResponse(
                roomService.update(roomMapper.requestToRoom(id, roomRequestDTO)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteById(id);
    }

}
