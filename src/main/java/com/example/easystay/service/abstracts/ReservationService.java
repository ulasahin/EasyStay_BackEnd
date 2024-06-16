package com.example.easystay.service.abstracts;

import com.example.easystay.model.entity.Reservation;
import com.example.easystay.service.dtos.requests.reservation.AddReservationRequest;
import com.example.easystay.service.dtos.requests.reservation.UpdateReservationRequest;
import com.example.easystay.service.dtos.responses.reservation.AddReservationResponse;
import com.example.easystay.service.dtos.responses.reservation.ListMyReservationResponse;
import com.example.easystay.service.dtos.responses.reservation.ListReservationResponse;

import java.util.List;

public interface ReservationService {
    List<ListReservationResponse> getAll();

    AddReservationResponse add(AddReservationRequest request);

    List<ListMyReservationResponse> getUserReservations();

    AddReservationResponse update(UpdateReservationRequest request);

    void cancelReservation(long id);
}
