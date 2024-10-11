package com.example.hotel_booking.mapper;

import com.example.hotel_booking.DTO.*;
import com.example.hotel_booking.model.Room;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(RoomMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper {

    Room requestToRoom(RoomRequestDTO roomRequestDTO);

    @Mapping(source = "roomId", target = "id")
    Room requestToRoom(Long roomId, RoomRequestDTO roomRequestDTO);

    RoomResponseDTO roomToRoomResponse(Room room);

    default RoomListResponseDTO roomListToRoomListResponse(List<Room> rooms) {
        RoomListResponseDTO roomListResponseDTO = new RoomListResponseDTO();
        roomListResponseDTO.setRoomResponses(rooms.stream()
                .map(this::roomToRoomResponse).collect(Collectors.toList()));
        return roomListResponseDTO;
    }

}
