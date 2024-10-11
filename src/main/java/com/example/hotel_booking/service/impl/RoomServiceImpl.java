package com.example.hotel_booking.service.impl;

import com.example.hotel_booking.DTO.RoomFilter;
import com.example.hotel_booking.model.Room;
import com.example.hotel_booking.repository.RoomRepository;
import com.example.hotel_booking.repository.RoomSpecification;
import com.example.hotel_booking.service.RoomService;
import com.example.hotel_booking.utils.BeanUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public List<Room> filterBy(RoomFilter roomFilter) {
        return roomRepository.findAll(RoomSpecification.withFilter(roomFilter),
                PageRequest.of(
                        roomFilter.getPageNumber(), roomFilter.getPageSize()
                )).getContent();
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Комната с ID {0} не найдена", id
                )));
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room update(Room room) {
        Room existedRoom = roomRepository.findById(room.getId()).get();
        BeanUtils.copyNonNullProperties(room, existedRoom);
        return roomRepository.save(existedRoom);
    }

    @Override
    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }
}
