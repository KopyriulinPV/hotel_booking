package com.example.hotel_booking.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String roomNumber;
    private double price;
    private int maxOccupancy;

    @ElementCollection
    private List<LocalDate> unavailableDates;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

}
