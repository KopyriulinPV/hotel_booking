package com.example.hotel_booking.repository;

import com.example.hotel_booking.DTO.RoomFilter;
import com.example.hotel_booking.model.Booking;
import com.example.hotel_booking.model.Room;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

public interface RoomSpecification {

    static Specification<Room> withFilter(RoomFilter roomFilter) {
        return Specification.where(byId(roomFilter.getId()))
                .and(byRoomNumber(roomFilter.getRoomNumber()))
                .and(byPriceRange(roomFilter.getMinPrice(), roomFilter.getMaxPrice()))
                .and(byMaxOccupancy(roomFilter.getMaxOccupancy()))
                .and(byResidenceTime(roomFilter.getCheckInDate(), roomFilter.getCheckOutDate()))
                .and(byIdHotel(roomFilter.getId_hotel()));
    }

    static Specification<Room> byId(Long id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("id"), id);
        };
    }

    static Specification<Room> byRoomNumber(String roomNumber) {
        return (root, query, criteriaBuilder) -> {
            if (roomNumber == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("roomNumber"), roomNumber);
        };
    }

    static Specification<Room> byPriceRange(Double minPrice, Double maxPrice) {
        return ((root, query, criteriaBuilder) -> {
            if (minPrice == null && maxPrice == null) {
                return null;
            }
            if (minPrice == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("cost"), maxPrice);
            }
            if (maxPrice == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), minPrice);
            }
            return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
        });
    }

    static Specification<Room> byMaxOccupancy(Integer maxOccupancy) {
        return (root, query, criteriaBuilder) -> {
            if (maxOccupancy == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("maxOccupancy"), maxOccupancy);
        };
    }

    static Specification<Room> byResidenceTime(LocalDate checkInDate, LocalDate checkOutDate) {

        return (root, query, criteriaBuilder) -> {
            if (checkInDate == null || checkOutDate == null) {
                return criteriaBuilder.conjunction();
            }

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Booking> booking = subquery.from(Booking.class);

            Predicate qq = criteriaBuilder.greaterThanOrEqualTo(booking.get("checkInDate"), checkInDate);
            Predicate ww = criteriaBuilder.lessThanOrEqualTo(booking.get("checkInDate"), checkOutDate);

            Predicate qq2 = criteriaBuilder.greaterThanOrEqualTo(booking.get("checkOutDate"), checkInDate);
            Predicate ww2 = criteriaBuilder.lessThanOrEqualTo(booking.get("checkOutDate"), checkOutDate);

            Predicate qq3 = criteriaBuilder.lessThanOrEqualTo(booking.get("checkInDate"), checkInDate);
            Predicate ww3 = criteriaBuilder.greaterThanOrEqualTo(booking.get("checkOutDate"), checkOutDate);


            subquery.select(booking.get("room").get("id"))
                    .where(
                            criteriaBuilder.or(
                                    criteriaBuilder.and(qq, ww),
                                    criteriaBuilder.and(qq2, ww2),
                                    criteriaBuilder.and(qq3, ww3)
                            )
                    );

            return criteriaBuilder.not(root.get("id").in(subquery));
        };
    }

    static boolean  isOverlapUsingLocalDateAndDuration(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        long overlap = Math.min(end1.toEpochDay(), end2.toEpochDay()) -
                Math.max(start1.toEpochDay(), start2.toEpochDay());

        return overlap >= 0;
    }

    static Specification<Room> byIdHotel(Long id_hotel) {
        return (root, query, criteriaBuilder) -> {
            if (id_hotel == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("hotel").get("id"), id_hotel);
        };
    }
}
