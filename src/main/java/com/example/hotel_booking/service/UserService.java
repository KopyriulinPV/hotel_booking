package com.example.hotel_booking.service;

import com.example.hotel_booking.model.User;
import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User findByUserName(String userName);

    User save(User user);

    User update(User user);

    void deleteById(Long id);

    User findByUsername(String username);
}
