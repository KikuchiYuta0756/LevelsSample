package com.example.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        // パスワードが空白の場合は許可
        if (password == null || password.trim().isEmpty()) {
            return true;
        }
    	
        if (password == null || password.length() < 7 || password.length() > 15) {
            return false;
        }

        int count = 0;
        if (password.matches(".*[A-Z].*")) count++;
        if (password.matches(".*[a-z].*")) count++;
        if (password.matches(".*\\d.*")) count++;
        if (password.matches(".*[!@#$%^&*].*")) count++;

        return count >= 3;
    }
}
