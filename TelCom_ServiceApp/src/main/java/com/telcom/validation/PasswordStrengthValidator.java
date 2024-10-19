package com.telcom.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordStrengthValidator  implements ConstraintValidator<PasswordStrength, String>{
	
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return false; // handle @NotNull separately if needed
		}
		if (value.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{10,}$")) {
			return true; // Strong password
		}

		if (value.matches("^(?=.*[a-zA-Z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{customer.password.medium}")
			.addConstraintViolation();
			return false; // Medium password
		}

		if (value.matches("^[A-Za-z\\d]{6,}$")) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{customer.password.weak}")
			.addConstraintViolation();
			return false; // Weak password
		}

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate("{customer.password.invalid}")
		.addConstraintViolation();
		return false; // Does not meet any strength criteria

	}

}
