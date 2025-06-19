package com.example.validator.field;

import com.example.locale.Message;

import java.util.List;

public class StreetValidator extends AbstractFieldValidatorHandler {

    private static final String STREET_REGEX = "^[a-zA-Zа-яА-ЯіІїЇєЄ0-9\\s.,-]{2,50}$";

    StreetValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final StreetValidator INSTANCE = new StreetValidator(FieldValidatorKey.STREET);
    }

    public static StreetValidator getInstance() {
        return StreetValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isEmpty() || !fieldValue.matches(STREET_REGEX)) {
            errors.add(Message.INVALID_STREET);
        }
    }
}
