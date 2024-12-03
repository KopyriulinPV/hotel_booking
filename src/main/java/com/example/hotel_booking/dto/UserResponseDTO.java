package com.example.hotel_booking.dto;

import com.example.hotel_booking.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String username;
    private String password;
    private String email;
    private Role role;

}
