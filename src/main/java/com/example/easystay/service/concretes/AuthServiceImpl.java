package com.example.easystay.service.concretes;

import com.example.easystay.core.service.JwtService;
import com.example.easystay.model.enums.Role;
import com.example.easystay.model.entity.User;
import com.example.easystay.repository.UserRepository;
import com.example.easystay.service.abstracts.AuthService;
import com.example.easystay.service.dtos.auth.LoginRequest;
import com.example.easystay.service.dtos.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        Authentication authentication =
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        if(!authentication.isAuthenticated()){
            throw new RuntimeException("E posta ya da şifre yanlış");
        }
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("UserId", user.getId());
        extraClaims.put("UserName", user.getFirstName());
        extraClaims.put("UserEmail", user.getEmail());
        extraClaims.put("Role",user.getRole());
        return jwtService.generateToken(user.getUsername(), extraClaims);
    }

    @Override
    public void register(RegisterRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CUSTOMER);
        userRepository.save(user);
    }
}
