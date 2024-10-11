package com.example.hotel_booking.service.impl;

import com.example.hotel_booking.DTO.HotelFilter;
import com.example.hotel_booking.model.Hotel;
import com.example.hotel_booking.repository.HotelRepository;
import com.example.hotel_booking.repository.HotelSpecification;
import com.example.hotel_booking.service.HotelService;
import com.example.hotel_booking.utils.BeanUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public List<Hotel> filterBy(HotelFilter hotelFilter) {
        return hotelRepository.findAll(HotelSpecification.withFilter(hotelFilter),
                PageRequest.of(
                        hotelFilter.getPageNumber(), hotelFilter.getPageSize()
                )).getContent();
    }

    @Override
    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }



    @Override
    public Hotel findById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Отель с ID {0} не найден", id
                )));
    }

    @Override
    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel update(Hotel user) {
        Hotel existedHotel = hotelRepository.findById(user.getId()).get();
        BeanUtils.copyNonNullProperties(user, existedHotel);
        return hotelRepository.save(existedHotel);
    }

    @Override
    public void deleteById(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public void updateRating(Long id, double newMark) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));
        if (hotel.getRating() == 0) {
            hotel.setRating(newMark);
            hotel.setRatingCount(1);
            hotelRepository.save(hotel);
            return;
        }
        double currentRating = hotel.getRating();
        int currentNumberOfRating = hotel.getRatingCount();
        double totalRating = currentRating * currentNumberOfRating;
        totalRating = totalRating - currentRating + newMark;
        double newRating = round(totalRating / currentNumberOfRating, 1);
        hotel.setRating(newRating);
        hotel.setRatingCount(currentNumberOfRating + 1);
        hotelRepository.save(hotel);
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
