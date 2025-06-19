package com.example.validator.field;

import com.example.locale.Message;

import java.util.List;

public class CityValidator extends AbstractFieldValidatorHandler {

    private static final String CITY_REGEX = "^[a-zA-Zа-яА-ЯіІїЇєЄ\\s'-]{2,50}$";

    CityValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final CityValidator INSTANCE = new CityValidator(FieldValidatorKey.CITY);
    }

    public static CityValidator getInstance() {
        return CityValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isEmpty() || !fieldValue.matches(CITY_REGEX)) {
            errors.add(Message.INVALID_CITY);
        }
    }
}
