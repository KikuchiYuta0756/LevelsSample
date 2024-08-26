package com.example.validation;

import java.util.Calendar;
import java.util.Date;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CorrectDateValidator implements ConstraintValidator<ValidCorrectDate, Date> {

    @Override
    public void initialize(ValidCorrectDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(Date correctDate, ConstraintValidatorContext context) {
        Calendar currentCalendar = Calendar.getInstance();
        int currentMonth = currentCalendar.get(Calendar.MONTH);
        int currentYear = currentCalendar.get(Calendar.YEAR);

        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(correctDate);
        int dateMonth = dateCalendar.get(Calendar.MONTH);
        int dateYear = dateCalendar.get(Calendar.YEAR);

        // 日付が現在の年の現在の月の場合は有効
        if (dateYear == currentYear && dateMonth == currentMonth) {
            return true;
        }

        // もし現在の月が1月（0）の場合、前年の12月（11）も許可する
        if (currentMonth == Calendar.JANUARY) {
            return (dateYear == currentYear - 1 && dateMonth == Calendar.DECEMBER);
        }

        // それ以外の場合、前月が有効かどうかをチェック
        return (dateYear == currentYear && dateMonth == currentMonth - 1);
    }
}
