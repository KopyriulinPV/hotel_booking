package com.example.hotel_booking.web.controller;

import com.example.hotel_booking.DTO.*;
import com.example.hotel_booking.mapper.UserMapper;
import com.example.hotel_booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<UserListResponseDTO> getAllUsers() {
        return ResponseEntity.ok(userMapper.userListToUserListResponse(userService.findAll()));
    }

    @GetMapping("/userId/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userMapper.userToUserResponse(userService.findById(id)));
    }

    @GetMapping("/{userName}")
    public ResponseEntity<UserResponseDTO> getUserByUserName(@PathVariable String userName) {
        return ResponseEntity.ok(userMapper.userToUserResponse(userService.findByUserName(userName)));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToUserResponse(
                        userService.save(userMapper.requestToUser(userRequestDTO))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO hotelRequestDTO) {
        return ResponseEntity.ok(userMapper.userToUserResponse(
                userService.update(userMapper.requestToUser(id, hotelRequestDTO))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
