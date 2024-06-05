package com.example.easystay.controller;

import com.example.easystay.service.abstracts.AuthService;
import com.example.easystay.service.dtos.auth.LoginRequest;
import com.example.easystay.service.dtos.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auths")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("login")
    public String login(@RequestBody LoginRequest request){
        return authService.login(request);
    }
    @PostMapping("register")
    public void register(@RequestBody RegisterRequest request){
        authService.register(request);
    }
}
