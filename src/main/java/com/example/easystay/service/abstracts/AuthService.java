package com.example.easystay.service.abstracts;

import com.example.easystay.service.dtos.requests.auth.LoginRequest;
import com.example.easystay.service.dtos.requests.auth.RegisterRequest;

public interface AuthService {
    String login(LoginRequest request);
    void register(RegisterRequest request);
}
