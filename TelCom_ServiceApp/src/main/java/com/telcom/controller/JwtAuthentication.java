package com.telcom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telcom.dto.JwtResponse;
import com.telcom.dto.LoginDTO;
import com.telcom.security.CustomUserDetailsService;
import com.telcom.security.JwtHelper;

@RestController
@RequestMapping("/auth")
public class JwtAuthentication {
	
	
//    private UserDetailsService userDetailsService;
	@Autowired
	private CustomUserDetailsService userservice;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(JwtAuthentication.class);
    
    
    
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginwithAith(@RequestBody LoginDTO request) {
    	
    	logger.info("Entering into Authentication......" + request.getPhoneNo());

        this.doAuthenticate(request.getPhoneNo(), request.getPassword());
       

  //      UserDetails userDetails = userDetailsService.loadUserByUsername("Kamlesh Sahu");
        UserDetails userDetails =  userservice.loadUserByUsername(String.valueOf(request.getPhoneNo()));
        
        String token = this.helper.generateToken(userDetails);
        
        System.out.println(token);
       
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        logger.info("Exiting  From Authentication......");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(Long phoneNo, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(phoneNo, password);
        try {
        	
        	 manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }


}
