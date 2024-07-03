package com.example.easystay.service.concretes;

import com.example.easystay.core.exceptionhandling.exception.types.BusinessException;
import com.example.easystay.mapper.UserMapper;
import com.example.easystay.model.entity.User;
import com.example.easystay.repository.UserRepository;
import com.example.easystay.service.abstracts.UserService;
import com.example.easystay.service.dtos.requests.auth.RegisterRequest;
import com.example.easystay.service.dtos.requests.user.UpdateUserRequest;
import com.example.easystay.service.dtos.responses.user.AddUserResponse;
import com.example.easystay.service.dtos.responses.user.DeleteUserResponse;
import com.example.easystay.service.dtos.responses.user.ListUserResponse;
import com.example.easystay.service.dtos.requests.user.AddUserRequest;
import com.example.easystay.service.dtos.responses.user.UpdateUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service//Service olduğunu belirtmek için.
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<ListUserResponse> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(u -> new ListUserResponse(u.getFirstName(),u.getLastName(),u.getEmail())).toList();
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
        emailShouldNotExist(request.getEmail());

        userRepository.save(user);
        AddUserResponse responses = new AddUserResponse();
        responses.setLastName(user.getLastName());
        responses.setFirstName(user.getFirstName());
        return responses;
    }
    @Override
    public UpdateUserResponse update(UpdateUserRequest request) {
        User user = userRepository.findById(request.getId()).orElseThrow(()
                -> new BusinessException("Bu Id'ye sahip bir kullanıcı bulunmamıştır."));

        emailShouldNotExist(request.getEmail());

        //UserMapper UpdateRequest'i id ile işlem yaptığı için 56. satır böyle olmalı.
        UserMapper.INSTANCE.userFromUpdateRequest(request,user);

        user = userRepository.save(user);
        UpdateUserResponse response = UserMapper.INSTANCE.userFromUpdateResponse(user);
        return response;
    }

    @Override
    public DeleteUserResponse delete(long id) {
        User user = userRepository.findById(id).orElseThrow(()
                -> new BusinessException("Bu Id'ye sahip bir kullanıcı bulunmamıştır."));
        DeleteUserResponse response = UserMapper.INSTANCE.userFromDeleteResponse(user);
        userRepository.delete(user);
        return response;
    }

    @Override
    public ListUserResponse getById(long id) {
        User user = userRepository.findById(id).orElseThrow(()
                -> new BusinessException("Bu Id'ye sahip bir kullanıcı bulunmamıştır."));
        ListUserResponse response = UserMapper.INSTANCE.userFromGetResponse(user);
        return response;
    }

    @Override
    public User getUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Şu e-postaya ait kullanıcı bulunamadı:" + email));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       return userRepository.findByEmail(email).orElseThrow();
    }

    //Business Rules
    private void emailShouldNotExist(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            throw new BusinessException("Böyle bir email mevcut");
        }
    }
}
