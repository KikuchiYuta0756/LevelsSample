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
    	
        // 長さチェック
        if (password.length() < 7 || password.length() > 15) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("パスワードは7文字以上15文字以内でなければなりません。")
                   .addConstraintViolation();
            return false;
        }

        int count = 0;
        if (password.matches(".*[A-Z].*")) count++;
        if (password.matches(".*[a-z].*")) count++;
        if (password.matches(".*\\d.*")) count++;
        if (password.matches(".*[!@#$%^&*].*")) count++;

        if (count < 3) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("パスワードは小文字、大文字、数字、特殊文字のうち3種類以上を含む必要があります。")
                   .addConstraintViolation();
            return false;
        }

        return true;
    }
}
