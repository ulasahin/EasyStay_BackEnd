package com.example.easystay.service.dtos.requests.room;

import com.example.easystay.core.exceptionhandling.exception.problemdetails.ErrorMessages;
import com.example.easystay.model.enums.RoomType;
import com.example.easystay.model.enums.RoomStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddRoomRequest {
    @NotBlank(message = ErrorMessages.NOT_BLANK_ROOM_NUMBER)
    @Pattern(regexp = "\\d+",message = ErrorMessages.JUST_NUMERİC_CHAR_FOR_ROOM_NUMBER)
    private int roomNumber;

    @NotBlank(message = ErrorMessages.NOT_BLANK_ROOM_PRİCE)
    @Pattern(regexp = "\\d+",message = ErrorMessages.JUST_NUMERİC_CHAR_FOR_ROOM_PRİCE)
    private double price;

    @NotBlank(message = ErrorMessages.NOT_BLANK_ROOM_STATUS)
    private RoomStatus status;

    @NotBlank(message = ErrorMessages.NOT_BLANK_ROOM_TYPE)
    private RoomType roomType;
}
