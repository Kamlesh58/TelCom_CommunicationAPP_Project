package com.telcom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.telcom.dto.CallDetailsDTO;
import com.telcom.dto.CustomerDTO;
import com.telcom.dto.FriendFamilyDTO;
import com.telcom.dto.LoginDTO;
import com.telcom.dto.RegisterDTO;
import com.telcom.entity.CallDetails;
import com.telcom.entity.Customer;
import com.telcom.entity.FriendFamily;
import com.telcom.exception.TelcomException;
import com.telcom.repository.CallDetailsRepository;
import com.telcom.repository.CustomerRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
	

	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	CustomerRepository customerrepo;

	@Autowired
	CallDetailsRepository calldetailsrepo;
	
	@Autowired
	PasswordEncoder passwordencoder;


	
	public void registerCustomer(RegisterDTO custdto) throws TelcomException {
		
		logger.info("Entering into Create Request for Customer "+ custdto);
		
		Optional<Customer> existdata = customerrepo.findById(custdto.getPhoneNo());
		if(existdata.isPresent()) {
			throw new TelcomException("Customer is Already Registered");
		}
		Customer cust = custdto.prepareEntity(custdto);
		cust.setPassword(passwordencoder.encode(custdto.getPassword()));
		customerrepo.save(cust);
		
		logger.info("Exit From Create Request for Customer "+ custdto);
		
	}

	@Override
	public boolean loginCustomer(LoginDTO login) throws TelcomException {
		Boolean flag = false;
		logger.info(" Entering Login request for Customer {} with Password{} ",login.getPhoneNo());
		Optional<Customer>cust = customerrepo.findById(login.getPhoneNo());
		
		if(cust == null) {
			throw new TelcomException("Could not found user ");
		}
		if(cust.isPresent() && cust.get() != null && passwordencoder.matches(login.getPassword(),cust.get().getPassword())) {
			flag = true;
		}
	    logger.info("Exiting Login request for Customer {} with Password{} ",login.getPhoneNo());
		return flag;
	}
	
	@Override
	public CustomerDTO getCustomerProfile(Long phoneNo)throws TelcomException {
		CustomerDTO custdto = null;
		logger.info("Profile request for Customer {}", phoneNo);
		
		Optional<Customer>cust = customerrepo.findById(phoneNo);
		if(cust.isPresent()) {
			custdto = CustomerDTO.prepareDTO(cust.get());
		}else {
			throw new TelcomException("Customer Not Found");
		}
		logger.info("Profile request for Customer {}",custdto);
		return custdto;
	}

	@Override
	public List<CallDetailsDTO> getCallDetails(Long phoneNo) throws TelcomException{
		
		logger.info("CallDetails request for Customer {} ",phoneNo);
		
		if(String.valueOf(phoneNo).length() != 10) {
			throw new TelcomException("phoneNo should be 10 digit");
		}
		
		List<CallDetails>calls  =calldetailsrepo.findBycalledBy(phoneNo);
		
		if(calls.isEmpty()) {
			throw new TelcomException("Call Details are not avaliable");
		}
		List<CallDetailsDTO>calldto = new ArrayList<>();
		
		for(CallDetails call : calls) {
			calldto.add(CallDetailsDTO.prepareDTO(call));
		}
		
		logger.info("CallDetails request for Customer {} ",calls);
		return calldto;
	}

	@Override
	public void saveFriend(Long phoneNo, FriendFamilyDTO frienddto)throws TelcomException {
		
		
		if(String.valueOf(phoneNo).length() != 10) {
			throw new TelcomException("phoneNo should be 10 digit");
		}
		
		frienddto.setPhoneNo(phoneNo);
		
		FriendFamily friend = frienddto.createFriend();
		
		List<FriendFamily> friendList = new ArrayList<>();
		friendList.add(friend);
		
		@SuppressWarnings("deprecation")
		Customer cust = customerrepo.getOne(phoneNo);
		cust.getFriends().add(friend);
		cust.setFriends(cust.getFriends());
	//	friendrepo.save(friend);
		customerrepo.save(cust);
		
	}

}
