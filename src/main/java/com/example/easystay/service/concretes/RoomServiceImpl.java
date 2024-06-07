package com.example.easystay.service.concretes;

import com.example.easystay.model.entity.Room;
import com.example.easystay.model.enums.RoomType;
import com.example.easystay.repository.RoomRepository;
import com.example.easystay.service.abstracts.RoomService;
import com.example.easystay.service.dtos.requests.room.AddRoomRequest;
import com.example.easystay.service.dtos.requests.room.UpdateRoomRequest;
import com.example.easystay.service.dtos.responses.room.AddRoomResponse;
import com.example.easystay.service.dtos.responses.room.ListRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    @Override
    public List<ListRoomResponse> getAll() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map(r -> new ListRoomResponse(r.getRoomNumber(),r.getPrice())).toList();
    }

    @Override
    public AddRoomResponse add(AddRoomRequest request) {
        Room room = new Room();
        room.setRoomNumber(request.getRoomNumber());
        room.setPrice(request.getPrice());
        room.setStatus(request.getStatus());
        roomRepository.save(room);
        AddRoomResponse addRoomResponse = new AddRoomResponse();
        addRoomResponse.setPrice(room.getPrice());
        addRoomResponse.setRoomNumber(request.getRoomNumber());
        return addRoomResponse;
    }

    @Override
    public AddRoomResponse update(UpdateRoomRequest request) {
        Room room = roomRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Böyle bir id yok"));
        room.setRoomNumber(request.getRoomNumber());
        room.setPrice(request.getPrice());
        room.setStatus(request.getStatus());
        roomRepository.save(room);
        AddRoomResponse addRoomResponse = new AddRoomResponse();
        addRoomResponse.setPrice(room.getPrice());
        addRoomResponse.setRoomNumber(request.getRoomNumber());
        return addRoomResponse;
    }


}
