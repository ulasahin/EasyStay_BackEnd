package com.example.easystay.core.mail;

import com.example.easystay.core.exceptionhandling.exception.types.BusinessException;
import com.example.easystay.model.entity.User;
import com.example.easystay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserRepository userRepository;

    @Value("${email.default}")
    private String EMAİL;

    public void sendEmailToUser(String userEmail,String subject,String body) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(
                ()-> new BusinessException("Böyle bir e-mail bulunamamıştır."));
        if (user != null) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject(subject);
            message.setText(body);
            message.setFrom(EMAİL);

            mailSender.send(message);
        }
    }
}
