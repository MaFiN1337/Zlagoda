package com.example.validator.field;

import com.example.locale.Message;

import java.util.List;

public class SellingPriceValidator extends AbstractFieldValidatorHandler {

    private static final String SELLING_PRICE_REGEX = "^\\d*\\.?\\d*$";

    SellingPriceValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final SellingPriceValidator INSTANCE = new SellingPriceValidator(FieldValidatorKey.SELLING_PRICE);
    }

    public static SellingPriceValidator getInstance() {
        return SellingPriceValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isEmpty() || !fieldValue.matches(SELLING_PRICE_REGEX)) {
            errors.add(Message.INVALID_SELLING_PRICE);
        }
    }
}
