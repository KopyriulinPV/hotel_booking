package com.example.hotel_booking.mapper;

import com.example.hotel_booking.dto.*;
import com.example.hotel_booking.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User requestToUser(UserRequestDTO userRequestDTO);

    @Mapping(source = "userId", target = "id")
    User requestToUser(Long userId, UserRequestDTO userRequestDTO);

    UserResponseDTO userToUserResponse(User hotel);

    default UserListResponseDTO userListToUserListResponse(List<User> hotels) {
        UserListResponseDTO userListResponseDTO = new UserListResponseDTO();
        userListResponseDTO.setUserResponses(hotels.stream()
                .map(this::userToUserResponse).collect(Collectors.toList()));
        return userListResponseDTO;
    }

}
