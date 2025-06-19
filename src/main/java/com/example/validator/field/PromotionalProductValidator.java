package com.example.validator.field;

import com.example.locale.Message;

import java.util.List;

public class PromotionalProductValidator extends AbstractFieldValidatorHandler {

    private static final String PROMOTIONAL_PRODUCT_REGEX = "^(true|false)$";

    PromotionalProductValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final PromotionalProductValidator INSTANCE = new PromotionalProductValidator(FieldValidatorKey.PROMOTIONAL_PRODUCT);
    }

    public static PromotionalProductValidator getInstance() {
        return PromotionalProductValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isEmpty() || !fieldValue.matches(PROMOTIONAL_PRODUCT_REGEX)) {
            errors.add(Message.INVALID_PROMOTIONAL_PRODUCT);
        }
    }
}