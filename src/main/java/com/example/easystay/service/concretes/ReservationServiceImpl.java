package com.example.easystay.service.concretes;

import com.example.easystay.mapper.ReservationMapper;
import com.example.easystay.model.entity.Reservation;
import com.example.easystay.model.entity.Room;
import com.example.easystay.model.entity.User;
import com.example.easystay.model.enums.ReservationStatus;
import com.example.easystay.repository.ReservationRepository;
import com.example.easystay.repository.RoomRepository;
import com.example.easystay.repository.UserRepository;
import com.example.easystay.service.abstracts.ReservationService;
import com.example.easystay.service.dtos.requests.reservation.AddReservationRequest;
import com.example.easystay.service.dtos.requests.reservation.UpdateReservationRequest;
import com.example.easystay.service.dtos.responses.reservation.AddReservationResponse;
import com.example.easystay.service.dtos.responses.reservation.ListMyReservationResponse;
import com.example.easystay.service.dtos.responses.reservation.ListReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    @Override
    public List<ListReservationResponse> getAll() {
        List<Reservation> reservationList = reservationRepository.findAll();
      return  reservationList.stream().map(r -> new ListReservationResponse(r.getTotalPrice())).toList();
    }

    @Override
    public AddReservationResponse add(AddReservationRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow();
        Room room = roomRepository.findById(request.getRoomId()).orElseThrow();
        Reservation reservation = ReservationMapper.INSTANCE.reservationFromRequest(request);
        reservation.setReservationStatus(ReservationStatus.PENDING);
        reservation.setUser(user);
        reservation.setRoom(room);
        reservation = reservationRepository.save(reservation);
        AddReservationResponse response = ReservationMapper.INSTANCE.reservationFromResponse(reservation);
        return response;
    }

    @Override
    public List<ListMyReservationResponse> getUserReservations() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); //tokendan kullanıcı adını al
        User user = userRepository.findByEmail(username).orElseThrow();
        Long userId = user.getId();
        List<Reservation> reservationList = reservationRepository.findByUserId(userId).stream().toList();
        return   reservationList.stream().filter(r -> r.getReservationStatus()==ReservationStatus.APPROVED).map(r -> new ListMyReservationResponse(r.getTotalPrice())).toList();

    }

    @Override
    public AddReservationResponse update(UpdateReservationRequest request) {
        Reservation reservation = reservationRepository.findById(request.getId()).orElseThrow();
        reservation.setReservationStatus(request.getStatus());
        if(request.getStatus()==ReservationStatus.REJECTED){
            reservationRepository.delete(reservation);
        }else {
            reservationRepository.save(reservation);
        }
        AddReservationResponse response = ReservationMapper.INSTANCE.reservationFromResponse(reservation);
        return response;
    }
}
