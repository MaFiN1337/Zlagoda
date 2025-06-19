package com.example.validator.field;

import com.example.locale.Message;

import java.util.List;

public class SumTotalValidator extends AbstractFieldValidatorHandler {

    private static final String SUM_TOTAL_REGEX = "^\\d*\\.?\\d*$";

    SumTotalValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final SumTotalValidator INSTANCE = new SumTotalValidator(FieldValidatorKey.SUM_TOTAL);
    }

    public static SumTotalValidator getInstance() {
        return SumTotalValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isEmpty() || !fieldValue.matches(SUM_TOTAL_REGEX)) {
            errors.add(Message.INVALID_SUM_TOTAL);
        }
    }
}
