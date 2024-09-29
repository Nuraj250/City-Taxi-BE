package com.example.city_Taxi.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j

public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationEmail(String toEmail, String username, String password) {
        String subject = "Welcome to City Taxi - Your Account Details";
        String body = createMessageBody(username, password);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    private String createMessageBody(String username, String password) {
        return "Dear " + username + ",\n\n"
                + "Welcome to City Taxi! Your account has been successfully created.\n"
                + "Here are your login credentials:\n"
                + "Username: " + username + "\n"
                + "Password: " + password + "\n\n"
                + "Please keep this information safe and secure.\n\n"
                + "Best regards,\n"
                + "City Taxi Team";
    }
}
