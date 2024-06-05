package com.example.easystay.mapper;

import com.example.easystay.model.entity.Reservation;
import com.example.easystay.service.dtos.responses.reservation.ListReservationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

}
