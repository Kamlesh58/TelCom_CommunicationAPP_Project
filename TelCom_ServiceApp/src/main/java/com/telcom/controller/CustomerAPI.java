package com.telcom.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telcom.dto.CallDetailsDTO;
import com.telcom.dto.CustomerDTO;
import com.telcom.dto.FriendFamilyDTO;
import com.telcom.dto.LoginDTO;
import com.telcom.dto.PlanDTO;
import com.telcom.dto.RegisterDTO;
import com.telcom.exception.TelcomException;
import com.telcom.service.CustomerService;
import com.telcom.service.PlanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
public class CustomerAPI {
	
	public final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	CustomerService customerservice;
	
	@Autowired
	PlanService planservice;
	
	@PostMapping("/register")
	public ResponseEntity<String> createCustomerandsave(@Valid @RequestBody RegisterDTO custDTO) throws TelcomException{
		customerservice.registerCustomer(custDTO);
		String message = "Customer Created Successfully ";
		return new ResponseEntity<>(message , HttpStatus.CREATED);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginCustomers(@Valid @RequestBody LoginDTO  login) throws TelcomException{
		boolean ans = customerservice.loginCustomer(login);
		String message = null;
		if(ans == true) {
			message = "Login  Successfully ";
		}else {
			message = "Invalid User and Password ";
		}
		return new ResponseEntity<>(message , HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/customer/{phoneNo}")
	public ResponseEntity<CustomerDTO> getFullProfile(@PathVariable Long phoneNo)throws TelcomException{
		CustomerDTO ans = customerservice.getCustomerProfile(phoneNo);
		return new ResponseEntity<>(ans , HttpStatus.OK);
	}
	
	@GetMapping("/customer/{phoneNo}/calldetails")
	public ResponseEntity<List<CallDetailsDTO>>getCustomerCallDetails(@PathVariable Long phoneNo)throws TelcomException{
		List<CallDetailsDTO> ans = customerservice.getCallDetails(phoneNo);
		return new ResponseEntity<>(ans, HttpStatus.OK);
	}

	@PostMapping("/save/{phoneNo}/friends")
	public ResponseEntity<String> saveFriendsDetails(@PathVariable Long phoneNo , @Valid @RequestBody FriendFamilyDTO fDTO) throws TelcomException{
		customerservice.saveFriend(phoneNo, fDTO);
		String message = "Friend Details Saved Successfully ";
		return new ResponseEntity<>(message , HttpStatus.CREATED);
		
	}
	
	@GetMapping("/customer/plans")
	public ResponseEntity<List<PlanDTO>>getPlanDetails()throws TelcomException{
		List<PlanDTO> ans = planservice.getAllPlans();
		return new ResponseEntity<>(ans, HttpStatus.OK);
	}
	
	
	@PostMapping("/choose/plan/{phoneNo}/{planId}")
	public ResponseEntity<String> savePlanDetails(@PathVariable Long phoneNo ,@PathVariable int planId) throws TelcomException{
		planservice.choosePlan(planId, phoneNo);
		String message = "Plan Details Saved Successfully ";
		return new ResponseEntity<>(message , HttpStatus.CREATED);
		
	}
	
	
	
	//we can get the current user with the help of the Principal
	@GetMapping("/customer/current")
	public String getLoggedInUser(Principal principal) {
		return principal.getName();
		
	}
}
