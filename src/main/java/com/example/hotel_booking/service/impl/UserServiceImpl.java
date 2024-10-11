package com.example.hotel_booking.service.impl;

import com.example.hotel_booking.DTO.statistics.CreateUserEvent;
import com.example.hotel_booking.mapper.statistics.StatisticsMapper;
import com.example.hotel_booking.model.User;
import com.example.hotel_booking.repository.UserRepository;
import com.example.hotel_booking.service.UserService;
import com.example.hotel_booking.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final StatisticsMapper statisticsMapper;

    private final PasswordEncoder passwordEncoder;

    @Value("${app.kafka.kafkaMessageTopic2}")
    private String topicName;

    private final KafkaTemplate<String, CreateUserEvent> kafkaTemplate2;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUsername(userName).get();
    }

    @Override
    public User save(User user) {
        if ((!userRepository.existsByEmail(user.getEmail()))&&
                (!userRepository.existsByUsername(user.getUsername()))) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User user1 = userRepository.save(user);
            kafkaTemplate2.send(topicName, statisticsMapper.userToCreateUserEvent(user1));
            return user1;
        }
        return null;
    }

    @Override
    public User update(User user) {
        User existedUser = userRepository.findById(user.getId()).get();
        BeanUtils.copyNonNullProperties(user, existedUser);
        return userRepository.save(existedUser);
    }

    @Override
    public void deleteById(Long id) {
         userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Username not found!"));
    }
}
