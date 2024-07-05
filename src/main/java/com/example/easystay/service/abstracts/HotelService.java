package com.example.easystay.service.abstracts;

import java.util.List;

public interface HotelService {
    List<HotelResponse> getAll();
    List<HotelResponse> getByCity(String address);
}
