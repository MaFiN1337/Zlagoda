package com.example.validator.field;

import com.example.locale.Message;

import java.util.List;

public class VatValidator extends AbstractFieldValidatorHandler {

    private static final String VAT_REGEX = "^\\d*\\.?\\d*$";

    VatValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final VatValidator INSTANCE = new VatValidator(FieldValidatorKey.VAT);
    }

    public static VatValidator getInstance() {
        return VatValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isEmpty() || !fieldValue.matches(VAT_REGEX)) {
            errors.add(Message.INVALID_VAT);
        }
    }
}