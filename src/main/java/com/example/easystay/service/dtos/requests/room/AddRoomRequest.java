package com.example.easystay.service.dtos.requests.room;

import com.example.easystay.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddRoomRequest {
    private int roomNumber;
    private double price;
    private Status status;
}
