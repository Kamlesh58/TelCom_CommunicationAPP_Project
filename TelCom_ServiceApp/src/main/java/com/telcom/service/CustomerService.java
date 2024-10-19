package com.telcom.service;

import java.util.List;

import com.telcom.dto.CallDetailsDTO;
import com.telcom.dto.CustomerDTO;
import com.telcom.dto.FriendFamilyDTO;
import com.telcom.dto.LoginDTO;
import com.telcom.dto.RegisterDTO;
import com.telcom.exception.TelcomException;

public interface CustomerService {
	
    public void registerCustomer(RegisterDTO custdto) throws TelcomException;
	
	public boolean loginCustomer(LoginDTO login) throws TelcomException;
	
	public CustomerDTO getCustomerProfile(Long phoneNo) throws TelcomException;
	
    public List<CallDetailsDTO> getCallDetails(Long phoneNo) throws TelcomException;
    
    public void saveFriend(Long phoneNo , FriendFamilyDTO frienddto) throws TelcomException;

}
