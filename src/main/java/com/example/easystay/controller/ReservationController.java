package com.example.easystay.controller;

import com.example.easystay.model.entity.Reservation;
import com.example.easystay.model.entity.User;
import com.example.easystay.repository.ReservationRepository;
import com.example.easystay.repository.UserRepository;
import com.example.easystay.service.abstracts.ReservationService;
import com.example.easystay.service.dtos.requests.reservation.AddReservationRequest;
import com.example.easystay.service.dtos.requests.reservation.UpdateReservationRequest;
import com.example.easystay.service.dtos.responses.reservation.AddReservationResponse;
import com.example.easystay.service.dtos.responses.reservation.ListMyReservationResponse;
import com.example.easystay.service.dtos.responses.reservation.ListReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("reservs")
@RestController
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    private final UserRepository userRepository;


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<ListReservationResponse> getAll(){
       return reservationService.getAll();
    }
    @PostMapping
    public AddReservationResponse add(AddReservationRequest request){
     return reservationService.add(request);
    }

    @GetMapping("myreservations")
    public List<ListMyReservationResponse> getMyReservations(){
        return reservationService.getUserReservations();
    }

    @PutMapping
    public AddReservationResponse controlReservation(UpdateReservationRequest request){
        return reservationService.update(request);
    }
}
