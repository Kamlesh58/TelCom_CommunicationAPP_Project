package com.telcom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

    @Autowired
    private EmailService emailService;

    public String generateOtp() {
        // Example OTP generation logic (6-digit number)
        int otp = (int)(Math.random() * 900000) + 100000;
        return String.valueOf(otp);
    }

    public void sendOtpToUser(String email) {
        String otp = generateOtp();
        
        System.out.println("OTP IS : "+otp);
        emailService.sendOtpEmail(email, otp);
        // Store the OTP in the session or database for verification later
    }
}

