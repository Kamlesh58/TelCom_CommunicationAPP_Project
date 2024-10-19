package com.telcom.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationEntryPoint point;
	
	@Autowired
	private JwtAuthenticationFilter filter;
	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf->csrf.disable())
		     .cors(cors->cors.disable())
		     .authorizeHttpRequests(auth->auth.requestMatchers("/api/register").permitAll()
		    		 .requestMatchers("/auth/login").permitAll()
		    		 .requestMatchers("/sendOtp").permitAll()
		    		 .requestMatchers("/api/**").authenticated()
		    		 .anyRequest().authenticated())
		     .exceptionHandling(ex->ex.authenticationEntryPoint(point))
		     .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}
