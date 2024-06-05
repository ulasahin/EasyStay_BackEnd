package com.example.easystay.service.dtos.responses.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListReservationResponse {
    private String userFirstName;
    private String userLastName;
    private String roomType;
    private String roomPrice;
}
