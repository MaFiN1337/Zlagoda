package com.example.validator.field;

public enum FieldValidatorKey {

    SURNAME("surname"),
    NAME("name"),
    PATRONYMIC("patronymic"),
    SALARY("salary"),
    DATE("date"),
    PHONE_NUMBER("phone_number"),
    CITY("city"),
    STREET("street"),
    ZIP_CODE("zip_code"),
    CHARACTERISTICS("characteristics"),
    SELLING_PRICE("selling_price"),
    PRODUCTS_NUMBER("products_number"),
    PROMOTIONAL_PRODUCT("promotional_product"),
    PRINT_DATE("print_date"),
    SUM_TOTAL("sum_total"),
    VAT("vat"),
    PERCENT("percent");

    private String value;

    FieldValidatorKey(String value) {
    }

    public String getValue() {
        return value;
    }
}
