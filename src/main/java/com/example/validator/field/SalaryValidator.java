package com.example.validator.field;

import com.example.locale.Message;

import java.util.List;

public class SalaryValidator extends AbstractFieldValidatorHandler {

    private static final String SALARY_REGEX = "^\\d*\\.?\\d*$";

    SalaryValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final SalaryValidator INSTANCE = new SalaryValidator(FieldValidatorKey.SALARY);
    }

    public static SalaryValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isEmpty() || !fieldValue.matches(SALARY_REGEX)) {
            errors.add(Message.INVALID_SALARY);
        }
    }
}
