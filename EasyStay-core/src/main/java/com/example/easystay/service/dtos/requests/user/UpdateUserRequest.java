package com.example.easystay.service.dtos.requests.user;

import com.example.easystay.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private long id;

    @Size(min=0,max = 50,message = "İsim 3 ila 50 karakter arasında olabilir.")
    private String firstName;

    @Size(min=0,max = 50,message = "Soyisim 3 ila 50 karakter arasında olabilir.")
    private String lastName;

    @Email(message = "Geçerli bir Email değil.")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z]).{6,}$"
            ,message = "Şifre en az bir numerik, en az bir tane büyük harf içermeli ve en az 6 karakter olmalıdır.")
    private String password;

    @Pattern(regexp= "\\d+" , message = "Numara sadece numerik ifadeler içermelidir.")
    @Size(min = 11,max = 11, message = "Numara kısmı 11 haneli olmalı.")
    private String phoneNumber;

    private Role role;
}
