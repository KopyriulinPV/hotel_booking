package com.example.hotel_booking.mapper;

import com.example.hotel_booking.DTO.RoomRequestDTO;
import com.example.hotel_booking.DTO.RoomResponseDTO;
import com.example.hotel_booking.model.Room;
import com.example.hotel_booking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class RoomMapperDelegate implements RoomMapper{

    @Autowired
    private HotelService hotelService;

    @Override
    public Room requestToRoom(RoomRequestDTO roomRequestDTO) {
        Room room = new Room();
        room.setName(roomRequestDTO.getName());
        room.setDescription(roomRequestDTO.getDescription());
        room.setRoomNumber(roomRequestDTO.getRoomNumber());
        room.setPrice(roomRequestDTO.getPrice());
        room.setMaxOccupancy(roomRequestDTO.getMaxOccupancy());
        room.setUnavailableDates(roomRequestDTO.getUnavailableDates());
        room.setHotel(hotelService.findById(roomRequestDTO.getHotel_id()));
        return room;
    }

    @Override
    public Room requestToRoom(Long roomId, RoomRequestDTO roomRequestDTO) {
        Room room = requestToRoom(roomRequestDTO);
        room.setId(roomId);
        return room;
    }

    @Override
    public RoomResponseDTO roomToRoomResponse(Room room) {
        RoomResponseDTO roomResponseDTO = new RoomResponseDTO();
        roomResponseDTO.setId(room.getId());
        roomResponseDTO.setName(room.getName());
        roomResponseDTO.setDescription(room.getDescription());
        roomResponseDTO.setRoomNumber(room.getRoomNumber());
        roomResponseDTO.setPrice(room.getPrice());
        roomResponseDTO.setMaxOccupancy(room.getMaxOccupancy());
        roomResponseDTO.setUnavailableDates(room.getUnavailableDates());
        if (!(room.getHotel().getId() == null)) {
            roomResponseDTO.setHotel_id(room.getHotel().getId());
        }
        return roomResponseDTO;
    }
}
