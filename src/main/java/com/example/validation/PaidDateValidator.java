package com.example.validation;

import java.util.Calendar;
import java.util.Date;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PaidDateValidator implements ConstraintValidator<ValidPaidDate, Date> {
    @Override
    public void initialize(ValidPaidDate constraintAnnotation) {
    }

    @Override
    public boolean isValid(Date paidRequestDateApp, ConstraintValidatorContext context) {
        Calendar currentCalendar = Calendar.getInstance();
        int currentMonth = currentCalendar.get(Calendar.MONTH);
        int currentYear = currentCalendar.get(Calendar.YEAR);

        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(paidRequestDateApp);
        int dateMonth = dateCalendar.get(Calendar.MONTH);
        int dateYear = dateCalendar.get(Calendar.YEAR);

        // 日付が現在の年で今月の場合は有効
        if (dateYear == currentYear && dateMonth == currentMonth) {
            return true;
        }

        // 先月のチェック
        if (dateYear == currentYear && dateMonth == currentMonth - 1) {
            return true;
        }

        // 来月と再来月のチェック
        if (dateYear == currentYear && (dateMonth == currentMonth + 1 || dateMonth == currentMonth + 2)) {
            return true;
        }

        // 現在の月が1月または2月の場合、前年の12月または今月の2月を特別にチェック
        if (currentMonth == Calendar.JANUARY) {
            // 先月の12月
            if (dateYear == currentYear - 1 && dateMonth == Calendar.DECEMBER) {
                return true;
            }
            // 再来月の2月
            if (dateYear == currentYear && dateMonth == Calendar.MARCH) {
                return true;
            }
        } else if (currentMonth == Calendar.FEBRUARY) {
            // 先月の1月
            if (dateYear == currentYear && dateMonth == Calendar.JANUARY) {
                return true;
            }
            // 再来月の3月
            if (dateYear == currentYear && dateMonth == Calendar.APRIL) {
                return true;
            }
        }

        // それ以外は無効
        return false;
   }
}
