package com.example.easystay.service.concretes;

import com.example.easystay.core.exceptionhandling.exception.types.BusinessException;
import com.example.easystay.core.report.loging.CrudEventListener;
import com.example.easystay.core.security.service.JwtService;
import com.example.easystay.mapper.AuthMapper;
import com.example.easystay.model.enums.Role;
import com.example.easystay.model.entity.User;
import com.example.easystay.repository.UserRepository;
import com.example.easystay.service.abstracts.AuthService;
import com.example.easystay.service.dtos.requests.auth.LoginRequest;
import com.example.easystay.service.dtos.requests.auth.RegisterRequest;
import com.example.easystay.service.dtos.responses.auth.RegisterResponse;
import jakarta.persistence.EntityListeners;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()
                -> new BusinessException("Böyle bir e-mail bulunamamıştır."));

        Authentication authentication =
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        if(!authentication.isAuthenticated()){
            throw new BusinessException("E-posta ya da şifre yanlıştır.");
        }
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("UserId", user.getId());
        extraClaims.put("UserName", user.getFirstName());
        extraClaims.put("UserEmail", user.getEmail());
        extraClaims.put("Role",user.getRole());
        return jwtService.generateToken(user.getUsername(), extraClaims);
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        User user = new User();
        emailShouldNotExist(request);
        passwordConfirmation(request);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(Role.CUSTOMER);
        userRepository.save(user);
        RegisterResponse response = AuthMapper.INSTANCE.responseFromRegisterResponse(user);
        return response;
    }
    //Business Rules
    private void passwordConfirmation(RegisterRequest request){
        if (!(request.getPassword().equals(request.getPasswordConfirm()))){
            throw new BusinessException("Şifreler aynı değil.");
        }
    }
    private void emailShouldNotExist(RegisterRequest request){
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()){
            throw new BusinessException("Böyle bir e-mail zaten bulunmaktatır. Başka bir e-mail deneyiniz.");
        }
    }
}
