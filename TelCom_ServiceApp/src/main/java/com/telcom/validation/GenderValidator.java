package com.telcom.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidator  implements ConstraintValidator<ValidGender, Character> {

	@Override
	public boolean isValid(Character value, ConstraintValidatorContext context) {
		if(value == null) {
			return false;
		}
		return value == 'F' || value == 'M' || value == 'T';	}

}
