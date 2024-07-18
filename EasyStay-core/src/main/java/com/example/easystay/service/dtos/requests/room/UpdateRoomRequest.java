package com.example.easystay.service.dtos.requests.room;

import com.example.easystay.model.enums.RoomType;
import com.example.easystay.model.enums.RoomStatus;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoomRequest {
    private long id;
    @Pattern(regexp = "\\d+",message = "Oda numarası sadece numerik ifadeler içermelidir.")
    private int roomNumber;

    @Pattern(regexp = "\\d+",message = "Ücret kısmı sadece numarik ifadeler içermelidir.")
    private double price;

    private RoomType roomType;

    private RoomStatus status;
}
