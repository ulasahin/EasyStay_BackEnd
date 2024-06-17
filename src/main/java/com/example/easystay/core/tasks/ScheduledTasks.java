package com.example.easystay.core.tasks;

import com.example.easystay.core.exceptionhandling.exception.types.BusinessException;
import com.example.easystay.model.entity.Reservation;
import com.example.easystay.model.entity.Room;
import com.example.easystay.model.enums.Status;
import com.example.easystay.repository.ReservationRepository;
import com.example.easystay.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    @Scheduled(cron = "0 0 12 * * *") // 5 saniyede bir çalışır
    public void updateHotelStatus(){
        LocalDate today = LocalDate.now();
        List<Reservation> reservation = reservationRepository.findAll();
        for(Reservation reservation1 : reservation){
            if(reservation1.getCheckOutDate().isAfter(today)){
                Room room = reservation1.getRoom();
                room.setStatus(Status.CLEANING);
                roomRepository.save(room);
                scheduleRoomAvailabilityUpdate(room.getId(),30);
            }
        }

    }
    private void scheduleRoomAvailabilityUpdate(Long roomId, int delayInMinutes) {
        scheduler.schedule(() -> markRoomAsAvailable(roomId), delayInMinutes, TimeUnit.MINUTES);
    }
    private void markRoomAsAvailable(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new BusinessException("Room not found"));
        room.setStatus(Status.AVAILABLE);
        roomRepository.save(room);
        System.out.println("Oda " + roomId + " AVAILABLE olarak güncellendi: " + LocalDateTime.now());
    }
}
