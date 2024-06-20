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
public class AddUserRequest {
    @NotBlank(message = "İsim kısmı boş olamaz.")
    @Size(min=3,max = 50,message = "İsim 3 ila 50 karakter arasında olabilir.")
    private String firstName;

    @NotBlank(message = "Soyisim kısmı boş olamaz.")
    @Size(min=3,max = 50,message = "Soyisim 3 ila 50 karakter arasında olabilir.")
    private String lastName;

    @NotBlank(message = "E-mail alanı boş olamaz.")
    @Email(message = "Geçerli bir Email değil.")
    private String email;

    @NotBlank(message = "Şifre alanı boş olamaz.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z]).{6,}$"
            ,message = "Şifre en az bir numerik, en az bir tane büyük harf içermeli ve en az 6 karakter olmalıdır.")
    private String password;

    @NotBlank(message = "Numara kısmı boş olamaz.")
    @Pattern(regexp= "\\d+" , message = "Numara sadece numerik ifadeler içermelidir.")
    @Size(min = 10,max = 10, message = "Numara kısmı 10 haneli olmalı.")
    private String phoneNumber;

    private Role role;

}
