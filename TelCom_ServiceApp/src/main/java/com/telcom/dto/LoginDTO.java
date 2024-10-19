package com.telcom.dto;



import com.telcom.validation.PasswordStrength;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginDTO {
	
	
	@NotNull
	@Max(value = 9999999999L, message = "{customer.phoneNo.invalid}")
	@Min(value = 6000000000L, message = "{customer.phoneNo.invalid}")
	private long phoneNo;
	
	@PasswordStrength(message = "{customer.password.invalid}")
	private String password;

}
