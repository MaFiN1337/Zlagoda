package com.example.validator.entity;

import com.example.entity.Category;
import com.example.validator.field.AbstractFieldValidatorHandler;
import com.example.validator.field.FieldValidatorKey;
import com.example.validator.field.FieldValidatorsChainGenerator;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidator implements Validator<Category> {

    private final AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();

    CategoryValidator() {
    }

    private static class Holder {
        static final CategoryValidator INSTANCE = new CategoryValidator();
    }

    public static CategoryValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<String> validate(Category category) {
        List<String> errors = new ArrayList<>();

        fieldValidator.validateField(FieldValidatorKey.NAME, category.getName(), errors);

        return errors;
    }
}
