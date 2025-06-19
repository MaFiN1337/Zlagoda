package com.example.validator.field;

public final class FieldValidatorsChainGenerator {
    private FieldValidatorsChainGenerator() {

    }

    public static AbstractFieldValidatorHandler getFieldValidatorsChain() {
        AbstractFieldValidatorHandler surnameValidator = SurnameValidator.getInstance();
        AbstractFieldValidatorHandler nameValidator = NameValidator.getInstance();
        AbstractFieldValidatorHandler patronymicValidator = PatronymicValidator.getInstance();
        AbstractFieldValidatorHandler salaryValidator = SalaryValidator.getInstance();
        AbstractFieldValidatorHandler dateOfBirthValidator = DateOfBirthValidator.getInstance();
        AbstractFieldValidatorHandler dateOfStartValidator = DateOfStartValidator.getInstance();
        AbstractFieldValidatorHandler phoneNumberValidator = PhoneNumberValidator.getInstance();
        AbstractFieldValidatorHandler cityValidator = CityValidator.getInstance();
        AbstractFieldValidatorHandler streetValidator = StreetValidator.getInstance();
        AbstractFieldValidatorHandler zipCodeValidator = ZipCodeValidator.getInstance();
        AbstractFieldValidatorHandler characteristicsValidator = CharacteristicsValidator.getInstance();
        AbstractFieldValidatorHandler sellingPriceValidator = SellingPriceValidator.getInstance();
        AbstractFieldValidatorHandler productsNumberValidator = ProductsNumberValidator.getInstance();
        AbstractFieldValidatorHandler promotionalProductValidator = PromotionalProductValidator.getInstance();
        AbstractFieldValidatorHandler printDateValidator = PrintDateValidator.getInstance();
        AbstractFieldValidatorHandler sumTotalValidator = SumTotalValidator.getInstance();
        AbstractFieldValidatorHandler vatValidator = VatValidator.getInstance();
        AbstractFieldValidatorHandler percentValidator = PercentValidator.getInstance();

        surnameValidator.setNextFieldValidator(nameValidator);
        nameValidator.setNextFieldValidator(patronymicValidator);
        patronymicValidator.setNextFieldValidator(salaryValidator);
        salaryValidator.setNextFieldValidator(dateOfBirthValidator);
        dateOfBirthValidator.setNextFieldValidator(dateOfStartValidator);
        dateOfStartValidator.setNextFieldValidator(phoneNumberValidator);
        phoneNumberValidator.setNextFieldValidator(cityValidator);
        cityValidator.setNextFieldValidator(streetValidator);
        streetValidator.setNextFieldValidator(zipCodeValidator);
        zipCodeValidator.setNextFieldValidator(characteristicsValidator);
        characteristicsValidator.setNextFieldValidator(sellingPriceValidator);
        sellingPriceValidator.setNextFieldValidator(productsNumberValidator);
        productsNumberValidator.setNextFieldValidator(promotionalProductValidator);
        promotionalProductValidator.setNextFieldValidator(printDateValidator);
        printDateValidator.setNextFieldValidator(sumTotalValidator);
        sumTotalValidator.setNextFieldValidator(vatValidator);
        vatValidator.setNextFieldValidator(percentValidator);

        return surnameValidator;
    }
}
