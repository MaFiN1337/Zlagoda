package com.example.validator.field;

import com.example.locale.Message;

import java.util.List;

public class PhoneNumberValidator extends AbstractFieldValidatorHandler {

    private static final String PHONE_REGEX = "^(\\+)?\\d{7,12}$";

    PhoneNumberValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final PhoneNumberValidator INSTANCE = new PhoneNumberValidator(FieldValidatorKey.PHONE_NUMBER);
    }

    public static PhoneNumberValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isEmpty() || !fieldValue.matches(PHONE_REGEX)) {
            errors.add(Message.INVALID_PHONE_NUMBER);
        }
    }
}
