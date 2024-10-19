package com.telcom.dto;

import java.util.Date;

import com.telcom.entity.CallDetails;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallDetailsDTO {
	
	@NotNull
	@Max(value = 9999999999L, message = "{customer.phoneNo.invalid}")
	@Min(value = 6000000000L, message = "{customer.phoneNo.invalid}")
    long calledBy;
	
	@NotNull
	@Max(value = 9999999999L, message = "{customer.phoneNo.invalid}")
	@Min(value = 6000000000L, message = "{customer.phoneNo.invalid}")
	long calledTo;
	
	Date calledOn;
	
	int duration;
	
	
	
	public static CallDetailsDTO prepareDTO(CallDetails calldetails) {
		CallDetailsDTO calldto = new CallDetailsDTO();
		calldto.setCalledBy(calldetails.getCalledBy());
		calldto.setCalledOn(calldetails.getCalledOn());
		calldto.setCalledTo(calldetails.getCalledTo());
		calldto.setDuration(calldetails.getDuration());
		return calldto;
		
		
	}

}
