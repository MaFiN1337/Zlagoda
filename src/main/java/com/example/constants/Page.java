package com.example.constants;

public final class Page {

    public static final String PREFIX = "/WEB-INF/views/";
    public static final String ERROR_PREFIX = "errors";
    public static final String SUFFIX = ".jsp";
    private Page() {
    }

    public static final String HOME_VIEW = "/index" + SUFFIX;

    public static final String ALL_EMPLOYEES_VIEW = PREFIX + "allEmployees" + SUFFIX;
    public static final String ADD_UPDATE_EMPLOYEE_VIEW = PREFIX + "addUpdateEmployee" + SUFFIX;
    public static final String ALL_PRODUCTS_VIEW = PREFIX + "allProducts" + SUFFIX;
    public static final String ADD_UPDATE_PRODUCT_VIEW = PREFIX + "addUpdateProduct" + SUFFIX;
    public static final String ALL_STORE_PRODUCTS_VIEW = PREFIX + "allStoreProducts" + SUFFIX;
    public static final String ADD_UPDATE_STORE_PRODUCT_VIEW = PREFIX + "addUpdateStoreProduct" + SUFFIX;
    public static final String ALL_CHECKS_VIEW = PREFIX + "allChecks" + SUFFIX;
    public static final String ADD_UPDATE_CHECK_VIEW = PREFIX + "addUpdateCheck" + SUFFIX;
    public static final String ALL_SALES_VIEW = PREFIX + "allSales" + SUFFIX;
    public static final String ADD_UPDATE_SALE_VIEW = PREFIX + "addUpdateSale" + SUFFIX;
    public static final String ALL_CUSTOMER_CARDS_VIEW = PREFIX + "allCustomerCards" + SUFFIX;
    public static final String ADD_UPDATE_CUSTOMER_CARD_VIEW = PREFIX + "addUpdateCustomerCard" + SUFFIX;
    public static final String ALL_CATEGORIES_VIEW = PREFIX + "allCategories" + SUFFIX;
    public static final String ADD_UPDATE_CATEGORY_VIEW = PREFIX + "addUpdateCategory" + SUFFIX;

    public static final String PAGE_NOT_FOUND = PREFIX + ERROR_PREFIX + "pageNotFound" + SUFFIX;
}
