package com.example.validator.field;

import com.example.locale.Message;

import java.util.List;

public class DateValidator extends AbstractFieldValidatorHandler {

    private static final String DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";

    DateValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final DateValidator INSTANCE = new DateValidator(FieldValidatorKey.DATE);
    }

    public static DateValidator getInstance() {
        return DateValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isEmpty() || !fieldValue.matches(DATE_REGEX)) {
            errors.add(Message.INVALID_DATE_OF_BIRTH);
        }
    }
}