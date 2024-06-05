package com.example.easystay.controller;

import com.example.easystay.model.entity.User;
import com.example.easystay.service.abstracts.UserService;
import com.example.easystay.service.dtos.requests.user.AddUserRequest;
import com.example.easystay.service.dtos.responses.user.AddUserResponse;
import com.example.easystay.service.dtos.responses.user.ListUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public List<ListUserResponse> getAll(){
        return userService.getAll();
    }
    @PostMapping
    public AddUserResponse add(AddUserRequest request){
        return userService.add(request);
    }
    @GetMapping("/myprofile")
    public User getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.getUserByUsername(username);
    }
}
