package com.telcom.dto;


import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.telcom.entity.Customer;
import com.telcom.entity.FriendFamily;
import com.telcom.validation.PasswordStrength;
import com.telcom.validation.ValidGender;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreType
public class CustomerDTO {
	
	@NotNull
	@Max(value = 9999999999L, message = "{customer.phoneNo.invalid}")
	@Min(value = 6000000000L, message = "{customer.phoneNo.invalid}")
	private Long phoneNo;
	
	@NotNull
	@NotBlank(message = "{customer.name.blank}")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "{customer.name.invalid}")
	private String name;
	
	@NotNull(message = "{customer.age.notnull}")
    @Min(value = 11, message = "{customer.age.min}")
    @Max(value = 150, message = "{customer.age.max}")
	private Integer age;
	
	@NotBlank(message = "{customer.address.notblank}")
	@Size(min = 5, max = 100, message = "{customer.address.size}")
	private String address;
	
	@PasswordStrength(message = "{customer.password.invalid}")
	private String password;
	
	@ValidGender(message = "{customer.gender.invalid}")
	private char gender;
	
	private List<Long>friendAndFamily;
	
	private PlanDTO currentPlan;
	
	
	public static CustomerDTO prepareDTO(Customer customer) {
		
		CustomerDTO custdto = new CustomerDTO();
		custdto.setAge(customer.getAge());
		custdto.setGender(customer.getGender());
		custdto.setName(customer.getName());
		custdto.setPhoneNo(customer.getPhoneNo());
		custdto.setAddress(customer.getAddress());
		
		PlanDTO plandto = PlanDTO.prepareDTO(customer.getPlan());
		custdto.setCurrentPlan(plandto);
		
		List<FriendFamily> friends = customer.getFriends();
		List<Long>friendList = new ArrayList<>();
		
		for(FriendFamily f1 : friends) {
			friendList.add(f1.getFriendAndFamily());
		}
		
		custdto.setFriendAndFamily(friendList);
		return custdto;
		
	}
	
	public static Customer prepareEntity(CustomerDTO custdto) {
		
		Customer cust = new Customer();
		cust.setAge(custdto.getAge());
		cust.setGender(custdto.getGender());
		cust.setName(custdto.getName());
		cust.setPhoneNo(custdto.getPhoneNo());
		cust.setAddress(custdto.getAddress());
		cust.setPassword(custdto.getPassword());

		
		return cust;
	}
	

}
