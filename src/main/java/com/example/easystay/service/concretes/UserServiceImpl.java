package com.example.easystay.service.concretes;

import com.example.easystay.model.entity.User;
import com.example.easystay.repository.UserRepository;
import com.example.easystay.service.abstracts.UserService;
import com.example.easystay.service.dtos.responses.user.AddUserResponse;
import com.example.easystay.service.dtos.responses.user.ListUserResponse;
import com.example.easystay.service.dtos.requests.user.AddUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<ListUserResponse> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(u -> new ListUserResponse(u.getFirstName(),u.getLastName())).toList();
    }

    @Override
    public AddUserResponse add(AddUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(request.getRole());
        userRepository.save(user);
        AddUserResponse responses = new AddUserResponse();
        responses.setLastName(user.getLastName());
        responses.setFirstName(user.getFirstName());
        return responses;
    }

    @Override
    public User getUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + email));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return userRepository.findByEmail(username).orElseThrow();
    }
}
