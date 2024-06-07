package com.example.easystay.controller;

import com.example.easystay.model.entity.Room;
import com.example.easystay.model.enums.RoomType;
import com.example.easystay.repository.RoomRepository;
import com.example.easystay.service.abstracts.RoomService;
import com.example.easystay.service.dtos.requests.room.AddRoomRequest;
import com.example.easystay.service.dtos.requests.room.UpdateRoomRequest;
import com.example.easystay.service.dtos.responses.room.AddRoomResponse;
import com.example.easystay.service.dtos.responses.room.ListRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final RoomRepository roomRepository;

    @GetMapping
    private List<ListRoomResponse> getAll(){
        return roomService.getAll();
    }
    @GetMapping("/getByRoomType")
    private List<Room> getRoomType(@RequestParam RoomType roomType){
        return roomRepository.findByRoomType(roomType);
    }
    @PostMapping
    private AddRoomResponse add(AddRoomRequest request){
        return roomService.add(request);
    }

    @PutMapping
    private AddRoomResponse update(UpdateRoomRequest request)
    {return roomService.update(request);
    }
}
