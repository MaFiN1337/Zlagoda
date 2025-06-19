package com.example.validator.field;

import com.example.locale.Message;

import java.util.List;

public class PercentValidator extends AbstractFieldValidatorHandler {

    private static final String PERCENT_REGEX = "^\\d+$";

    PercentValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final PercentValidator INSTANCE = new PercentValidator(FieldValidatorKey.PERCENT);
    }

    public static PercentValidator getInstance() {
        return PercentValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isEmpty() || !fieldValue.matches(PERCENT_REGEX)) {
            errors.add(Message.INVALID_PERCENT);
        }
    }
}