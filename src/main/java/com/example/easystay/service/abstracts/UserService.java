package com.example.easystay.service.abstracts;

import com.example.easystay.model.entity.User;
import com.example.easystay.service.dtos.responses.user.AddUserResponse;
import com.example.easystay.service.dtos.responses.user.ListUserResponse;
import com.example.easystay.service.dtos.requests.user.AddUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<ListUserResponse> getAll();
    AddUserResponse add(AddUserRequest request);

    User getUserByUsername(String username);
}
