package com.example.hotel_booking.web.controller;

import com.example.hotel_booking.dto.*;
import com.example.hotel_booking.mapper.UserMapper;
import com.example.hotel_booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping
    public UserListResponseDTO getAllUsers() {
        return userMapper.userListToUserListResponse(userService.findAll());
    }

    @GetMapping("/userId/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userMapper.userToUserResponse(userService.findById(id));
    }

    @GetMapping("/{userName}")
    public UserResponseDTO getUserByUserName(@PathVariable String userName) {
        return userMapper.userToUserResponse(userService.findByUserName(userName));
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return userMapper.userToUserResponse(
                        userService.save(userMapper.requestToUser(userRequestDTO)));
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id, @RequestBody UserRequestDTO hotelRequestDTO) {
        return userMapper.userToUserResponse(
                userService.update(userMapper.requestToUser(id, hotelRequestDTO)));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
