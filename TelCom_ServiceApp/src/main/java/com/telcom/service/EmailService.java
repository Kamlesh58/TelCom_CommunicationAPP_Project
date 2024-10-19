package com.telcom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        
        System.out.println("TO : "+to);
        System.out.println("OTP : "+otp);
        
        message.setTo(to);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP code is: " + otp);
        
        mailSender.send(message);
        
        System.out.println("OTP Done : "+otp);
        
        
    }
}
