package com.example.easystay.service.dtos.requests.room;

import com.example.easystay.model.enums.Status;
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
    @NotBlank(message = "Oda numarası kısmı boş olamaz.")
    @Pattern(regexp = "\\d+",message = "Oda numarası sadece numarik ifadeler içermelidir.")
    private int roomNumber;

    @NotBlank(message = "Ücret kısmı boş olamaz.")
    @Pattern(regexp = "\\d+",message = "Ücret kısmı sadece numarik ifadeler içermelidir.")
    private double price;

    @NotBlank(message = "Durum kısmı boş olamaz.")
    private Status status;
}
