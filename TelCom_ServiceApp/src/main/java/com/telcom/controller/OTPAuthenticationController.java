package com.telcom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telcom.service.OtpService;

@RestController
public class OTPAuthenticationController {
	
	@Autowired
    private OtpService otpService;
	
    private Logger logger = LoggerFactory.getLogger(OTPAuthenticationController.class);

    @PostMapping("/sendOtp")
    public String sendOtp(@RequestParam String email) {
    	
    	logger.info("Sending Email To " +email);
        otpService.sendOtpToUser(email);
        
        logger.info("End Sending Email To " +email);
        return "OTP sent successfully!";
    }

}
