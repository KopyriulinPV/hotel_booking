package com.example.hotel_booking.repository;

import com.example.hotel_booking.dto.HotelFilter;
import com.example.hotel_booking.model.Hotel;
import org.springframework.data.jpa.domain.Specification;


public interface HotelSpecification {

    static Specification<Hotel> withFilter(HotelFilter hotelFilter) {
        return Specification.where(byId(hotelFilter.getId()))
                .and(byHotelName(hotelFilter.getHotelName()))
                .and(byHeadline(hotelFilter.getHeadline()))
                .and(byCity(hotelFilter.getCity()))
                .and(byAddress(hotelFilter.getAddress()))
                .and(byDistanceFromCenter(hotelFilter.getDistanceFromCenter()))
                .and(byRating(hotelFilter.getRating()))
                .and(byRatingCount(hotelFilter.getRatingCount()));
    }

    static Specification<Hotel> byId(Long id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("id"), id);
        };
    }

    static Specification<Hotel> byHotelName(String hotelName) {
        return (root, query, criteriaBuilder) -> {
            if (hotelName == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("hotelName"), hotelName);
        };
    }

    static Specification<Hotel> byHeadline(String headline) {
        return (root, query, criteriaBuilder) -> {
            if (headline == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("headline"), headline);
        };
    }

    static Specification<Hotel> byCity(String city) {
        return (root, query, criteriaBuilder) -> {
            if (city == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("city"), city);
        };
    }

    static Specification<Hotel> byAddress(String address) {
        return (root, query, criteriaBuilder) -> {
            if (address == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("address"), address);
        };
    }

    static Specification<Hotel> byDistanceFromCenter(Double distanceFromCenter) {
        return (root, query, criteriaBuilder) -> {
            if (distanceFromCenter == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("distanceFromCenter"), distanceFromCenter);
        };
    }

    static Specification<Hotel> byRating(Double rating) {
        return (root, query, criteriaBuilder) -> {
            if (rating == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("rating"), rating);
        };
    }

    static Specification<Hotel> byRatingCount(Integer ratingCount) {
        return (root, query, criteriaBuilder) -> {
            if (ratingCount == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("ratingCount"), ratingCount);
        };
    }
}
