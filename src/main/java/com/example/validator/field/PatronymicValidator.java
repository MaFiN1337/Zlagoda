package com.example.validator.field;

import com.example.locale.Message;

import java.util.List;

public class PatronymicValidator extends AbstractFieldValidatorHandler {
    private static final String PATRONYMIC_REGEX = "^[A-Za-zА-ЯІЇЄа-яіїє]+([\\s’'-][A-Za-zА-ЯІЇЄа-яіїє]+)*$";

    PatronymicValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final PatronymicValidator INSTANCE = new PatronymicValidator(FieldValidatorKey.PATRONYMIC);
    }

    public static PatronymicValidator getInstance() {
        return PatronymicValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isEmpty() || !fieldValue.matches(PATRONYMIC_REGEX)) {
            errors.add(Message.INVALID_PATRONYMIC);
        }
    }
}