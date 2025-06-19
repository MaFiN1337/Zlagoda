package com.example.validator.field;

import com.example.locale.Message;

import java.util.List;

public class DateOfBirthValidator extends AbstractFieldValidatorHandler {

    private static final String DATE_OF_BIRTH_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";

    DateOfBirthValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final DateOfBirthValidator INSTANCE = new DateOfBirthValidator(FieldValidatorKey.DATE_OF_BIRTH);
    }

    public static DateOfBirthValidator getInstance() {
        return DateOfBirthValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isEmpty() || !fieldValue.matches(DATE_OF_BIRTH_REGEX)) {
            errors.add(Message.INVALID_DATE_OF_BIRTH);
        }
    }
}