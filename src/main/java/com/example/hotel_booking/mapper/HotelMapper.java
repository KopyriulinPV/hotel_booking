package com.example.hotel_booking.mapper;

import com.example.hotel_booking.dto.HotelListResponseDTO;
import com.example.hotel_booking.dto.HotelRequestDTO;
import com.example.hotel_booking.dto.HotelResponseDTO;
import com.example.hotel_booking.model.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper {

    Hotel requestToHotel(HotelRequestDTO hotelRequestDTO);

    @Mapping(source = "hotelId", target = "id")
    Hotel requestToHotel(Long hotelId, HotelRequestDTO hotelRequestDTO);

    HotelResponseDTO hotelToHotelResponse(Hotel hotel);

    default HotelListResponseDTO hotelListToHotelListResponse(List<Hotel> hotels) {
        HotelListResponseDTO hotelListResponseDTO = new HotelListResponseDTO();
        hotelListResponseDTO.setHotelResponses(hotels.stream()
                .map(this::hotelToHotelResponse).collect(Collectors.toList()));
        return hotelListResponseDTO;
    }
}
