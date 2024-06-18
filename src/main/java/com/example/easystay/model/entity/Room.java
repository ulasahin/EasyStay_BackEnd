package com.example.easystay.model.entity;

import com.example.easystay.model.enums.RoomType;
import com.example.easystay.model.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity(name = "rooms")
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int roomNumber;
    private double price;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    @Enumerated(EnumType.STRING)
    private Status status = Status.AVAILABLE;
    @OneToMany(mappedBy = "room")
    private List<Reservation> reservationList;

}
