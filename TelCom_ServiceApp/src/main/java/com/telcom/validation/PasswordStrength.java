package com.telcom.validation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordStrength {

    String message() default "Password does not meet the strength requirements.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
