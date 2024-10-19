package com.telcom.security;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import com.telcom.entity.Customer;
import com.telcom.repository.CustomerRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
    	
        Long phoneNo = Long.parseLong(phoneNumber);  // Convert the string to Long
        Optional<Customer> user = userRepository.findById(phoneNo);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        UserBuilder builder = User.withUsername(phoneNumber)
                                  .password(user.get().getPassword());
                                 
        return builder.build();
    }
}

