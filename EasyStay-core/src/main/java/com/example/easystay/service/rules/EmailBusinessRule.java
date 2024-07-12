package com.example.easystay.service.rules;

import com.example.easystay.core.exceptionhandling.exception.types.BusinessException;
import com.example.easystay.core.mail.EmailService;
import com.example.easystay.model.entity.Reservation;
import com.example.easystay.model.entity.User;
import com.example.easystay.model.enums.Role;
import com.example.easystay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailBusinessRule {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailService emailService;

    @Value("${email.default}")
    private String EMAIL;

    public void findEmailAndSend(String userEmail,String subject,String body){
        User mail = userRepository.findByEmail(userEmail).orElseThrow(
                ()-> new BusinessException("Böyle bir e-mail bulunamamıştır."));

        if(mail != null){
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(mail.getEmail());
            message.setSubject(userEmail);
            message.setText(body);
            message.setFrom(EMAIL);

            mailSender.send(message);
        }
    }
    public void sendAllAdminCancelMail(Reservation reservation, User user){

        List<User> users = userRepository.findAll().stream().filter(u-> u.getRole()== Role.ADMIN).toList();
        List<String> users1 = users.stream().map(u->u.getEmail()).toList();

        for (String email : users1){
            emailService.sendEmailToUser(email,"Rezervasyon İptali"
                    ,"'"+user.getEmail()+"'"+" e-mail'e sahip kullanıcı "+"'"+reservation.getRoom().getRoomNumber()+"'"+" numaralı oda rezervasyonunu iptal etmiştir.");
        }
    }
}
