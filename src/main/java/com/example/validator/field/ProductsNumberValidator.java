package com.example.validator.field;

import com.example.locale.Message;

import java.util.List;

public class ProductsNumberValidator extends AbstractFieldValidatorHandler {

    private static final String PRODUCTS_NUMBER_REGEX = "^\\d+$";

    ProductsNumberValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static class Holder {
        static final ProductsNumberValidator INSTANCE = new ProductsNumberValidator(FieldValidatorKey.PRODUCTS_NUMBER);
    }

    public static ProductsNumberValidator getInstance() {
        return ProductsNumberValidator.Holder.INSTANCE;
    }

    @Override
    public void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isEmpty() || !fieldValue.matches(PRODUCTS_NUMBER_REGEX)) {
            errors.add(Message.INVALID_PRODUCTS_NUMBER);
        }
    }
}
