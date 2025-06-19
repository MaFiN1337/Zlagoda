package com.example.validator.field;

import com.example.locale.Message;

import java.util.List;

public class PrintDateValidator extends AbstractFieldValidatorHandler {

    private static final String PRINT_DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$";

    PrintDateValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final PrintDateValidator INSTANCE = new PrintDateValidator(FieldValidatorKey.PRINT_DATE);
    }

    public static PrintDateValidator getInstance() {
        return PrintDateValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isEmpty() || !fieldValue.matches(PRINT_DATE_REGEX)) {
            errors.add(Message.INVALID_PRINT_DATE);
        }
    }
}
