package com.example.validator.field;

import com.example.locale.Message;

import java.util.List;

public class DateOfStartValidator extends AbstractFieldValidatorHandler {

    private static final String DATE_OF_START_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";

    DateOfStartValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final DateOfStartValidator INSTANCE = new DateOfStartValidator(FieldValidatorKey.DATE_OF_START);
    }

    public static DateOfStartValidator getInstance() {
        return DateOfStartValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isEmpty() || !fieldValue.matches(DATE_OF_START_REGEX)) {
            errors.add(Message.INVALID_DATE_OF_START);
        }
    }
}
