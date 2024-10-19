package com.telcom.dto;

import com.telcom.entity.Customer;
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
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class RegisterDTO {
	
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

	
	

    public Customer prepareEntity(RegisterDTO custdto) {
		
		Customer cust = new Customer();
		cust.setAge(custdto.getAge());
		cust.setGender(custdto.getGender());
		cust.setName(custdto.getName());
		cust.setPhoneNo(custdto.getPhoneNo());
		cust.setAddress(custdto.getAddress());

		
		return cust;
	}

}
