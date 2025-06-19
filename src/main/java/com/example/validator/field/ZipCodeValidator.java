package com.example.validator.field;

import com.example.locale.Message;

import java.util.List;

public class ZipCodeValidator extends AbstractFieldValidatorHandler {

    private static final String ZIP_CODE_REGEX = "^\\d{5}$";

    ZipCodeValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final ZipCodeValidator INSTANCE = new ZipCodeValidator(FieldValidatorKey.ZIP_CODE);
    }

    public static ZipCodeValidator getInstance() {
        return ZipCodeValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isEmpty() || !fieldValue.matches(ZIP_CODE_REGEX)) {
            errors.add(Message.INVALID_ZIP_CODE);
        }
    }
}