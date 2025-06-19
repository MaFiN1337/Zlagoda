package com.example.validator.field;

import com.example.locale.Message;

import java.util.List;

public class CharacteristicsValidator extends AbstractFieldValidatorHandler {

    private static final String CHARACTERISTICS_REGEX = "^[A-Za-zА-ЯІЇЄа-яіїє]+([\\s’'-][A-Za-zА-ЯІЇЄа-яіїє]+)*$";

    CharacteristicsValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final CharacteristicsValidator INSTANCE = new CharacteristicsValidator(FieldValidatorKey.CHARACTERISTICS);
    }

    public static CharacteristicsValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isEmpty() || !fieldValue.matches(CHARACTERISTICS_REGEX)) {
            errors.add(Message.INVALID_CHARACTERISTICS);
        }
    }
}
