package com.telcom.service;

import org.springframework.stereotype.Service;

@Service
public class OtpVerificationService {

    // This should be more secure in a real application (e.g., use a cache or database)
    private String generatedOtp;

    public boolean verifyOtp(String userInputOtp) {
        return generatedOtp != null && generatedOtp.equals(userInputOtp);
    }

    // Store the generated OTP
    public void storeOtp(String otp) {
        this.generatedOtp = otp;
    }
}

