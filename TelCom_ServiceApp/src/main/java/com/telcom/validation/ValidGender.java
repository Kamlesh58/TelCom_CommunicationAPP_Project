package com.telcom.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;

@Constraint(validatedBy = GenderValidator.class)
@Target({ElementType.FIELD,ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidGender {

	 String message() default "{customer.gender.invalid}";

	 Class<?>[] groups() default {};

	 Class<? extends Payload>[] payload() default {};
	

}
