package com.example.controller;

import com.example.controller.command.Command;
import com.example.controller.command.HomeCommand;
import com.example.controller.command.PageNotFoundCommand;
import com.example.controller.command.category.*;
import com.example.controller.command.employee.*;
import com.example.controller.command.check.*;
import com.example.controller.command.store_product.*;
import com.example.controller.command.customer_card.*;
import com.example.controller.command.product.*;
import com.example.controller.command.product.DeleteProductCommand;
import com.example.controller.command.product.GetUpdateProductCommand;
import com.example.controller.command.product.PostAddProductCommand;
import com.example.controller.command.product.PostUpdateProductCommand;
import com.example.service.*;

public enum CommandEnum {

    PAGE_NOT_FOUND {
        {
            this.key = "GET:pageNotFound";
            this.command = new PageNotFoundCommand();
        }
    },
    HOME {
        {
            this.key = "GET:";
            this.command = new HomeCommand();
        }
    },
    // Categories Commands
    ALL_CATEGORIES {
        {
            this.key = "GET:manager/categories";
            this.command = new AllCategoriesCommand(CategoryService.getInstance());
        }
    },
    DELETE_CATEGORY {
        {
            this.key = "GET:manager/categories/deleteCategory";
            this.command = new DeleteCategoryCommand(CategoryService.getInstance());
        }
    },
    GET_ADD_CATEGORY {
        {
            this.key = "GET:manager/categories/addCategory";
            this.command = new GetAddCategoryCommand();
        }
    },
    POST_ADD_CATEGORY {
        {
            this.key = "POST:manager/categories/addCategory";
            this.command = new PostAddCategoryCommand(CategoryService.getInstance());
        }
    },
    GET_UPDATE_CATEGORY {
        {
            this.key = "GET:manager/categories/updateCategory";
            this.command = new GetUpdateCategoryCommand(CategoryService.getInstance());
        }
    },
    POST_UPDATE_CATEGORY {
        {
            this.key = "POST:manager/categories/updateCategory";
            this.command = new PostUpdateCategoryCommand(CategoryService.getInstance());
        }
    },
    SEARCH_CATEGORIES_BY_NAME_AND_SORT {
        {
            this.key = "POST:manager/categories/searchByNameAndSort";
            this.command = new SearchCategoriesByNameAndSortCommand(CategoryService.getInstance());
        }
    },
    SEARCH_CATEGORIES_BY_NAME {
        {
            this.key = "POST:manager/categories/searchByName";
            this.command = new SearchCategoriesByNameCommand(CategoryService.getInstance());
        }
    },

    // Employees Commands
    ALL_EMPLOYEES {
        {
            this.key = "GET:manager/employees";
            this.command = new AllEmployeesCommand(EmployeeService.getInstance());
        }
    },
    DELETE_EMPLOYEE {
        {
            this.key = "GET:manager/employees/deleteEmployee";
            this.command = new DeleteEmployeeCommand(EmployeeService.getInstance());
        }
    },
    GET_ADD_EMPLOYEE {
        {
            this.key = "GET:manager/employees/addEmployee";
            this.command = new GetAddEmployeeCommand();
        }
    },
    POST_ADD_EMPLOYEE {
        {
            this.key = "POST:manager/employees/addEmployee";
            this.command = new PostAddEmployeeCommand(EmployeeService.getInstance());
        }
    },
    GET_UPDATE_EMPLOYEE {
        {
            this.key = "GET:manager/employees/updateEmployee";
            this.command = new GetUpdateEmployeeCommand(EmployeeService.getInstance());
        }
    },
    POST_UPDATE_EMPLOYEE {
        {
            this.key = "POST:manager/employees/updateEmployee";
            this.command = new PostUpdateEmployeeCommand(EmployeeService.getInstance());
        }
    },
    SEARCH_EMPLOYEES_BY_SURNAME {
        {
            this.key = "POST:manager/employees/surname";
            this.command = new SearchEmployeesBySurnameCommand(EmployeeService.getInstance());
        }
    },
    SEARCH_EMPLOYEES_CASHIERS_SORTED_BY_SURNAME {
        {
            this.key = "POST:manager/employees/surnameCashiers";
            this.command = new SearchEmployeesCashiersSortedBySurnameCommand(EmployeeService.getInstance());
        }
    },
    SEARCH_EMPLOYEES_HAVING_SOLD_ALL_CATEGORIES_OF_PRODUCTS {
        {
            this.key = "POST:manager/employees/allCategoriesSold";
            this.command = new SearchEmployeesHavingSoldAllCategoriesOfProductsCommand(EmployeeService.getInstance());
        }
    },
    SEARCH_EMPLOYEE_SUM_OF_VAT_FOR_EACH_CATEGORY {
        {
            this.key = "POST:manager/employees/vatsForCategories";
            this.command = new SearchEmployeeSumOfVatForEachCategoryCommand(EmployeeService.getInstance());
        }
    },

    // Checks Commands
    ALL_CHECKS {
        {
            this.key = "GET:manager/checks";
            this.command = new AllChecksCommand(CheckService.getInstance(), Customer_cardService.getInstance(), EmployeeService.getInstance());
        }
    },
    DELETE_CHECK {
        {
            this.key = "GET:manager/checks/deleteCheck";
            this.command = new DeleteCheckCommand(CheckService.getInstance());
        }
    },
    GET_ADD_CHECK {
        {
            this.key = "GET:manager/checks/addCheck";
            this.command = new GetAddCheckCommand(CheckService.getInstance());
        }
    },
    GET_ADD_CHECK_CASHIER {
        {
            this.key = "GET:cashier/checks/addCheck";
            this.command = new GetAddCheckCommand(CheckService.getInstance());
        }
    },
    POST_ADD_CHECK {
        {
            this.key = "POST:manager/checks/addCheck";
            this.command = new PostAddCheckCommand(CheckService.getInstance(), Customer_cardService.getInstance(), EmployeeService.getInstance());
        }
    },
    POST_ADD_CHECK_CASHIER {
        {
            this.key = "POST:cashier/checks/addCheck";
            this.command = new PostAddCheckCommand(CheckService.getInstance(), Customer_cardService.getInstance(), EmployeeService.getInstance());
        }
    },
    GET_UPDATE_CHECK {
        {
            this.key = "GET:manager/checks/updateCheck";
            this.command = new GetUpdateCheckCommand(CheckService.getInstance(), Customer_cardService.getInstance(), EmployeeService.getInstance());
        }
    },
    POST_UPDATE_CHECK {
        {
            this.key = "POST:manager/checks/updateCheck";
            this.command = new PostUpdateCheckCommand(CheckService.getInstance(), Customer_cardService.getInstance(), EmployeeService.getInstance());
        }
    },

    // Customer Cards Commands
    ALL_CUSTOMER_CARDS {
        {
            this.key = "GET:manager/customerCards";
            this.command = new AllCustomerCardsCommand(Customer_cardService.getInstance());
        }
    },
    ALL_CUSTOMER_CARDS_CASHIER {
        {
            this.key = "GET:cashier/customerCards";
            this.command = new AllCustomerCardsCommand(Customer_cardService.getInstance());
        }
    },
    DELETE_CUSTOMER_CARD {
        {
            this.key = "GET:manager/customerCards/deleteCustomerCard";
            this.command = new DeleteCustomerCardCommand(Customer_cardService.getInstance());
        }
    },
    GET_ADD_CUSTOMER_CARD {
        {
            this.key = "GET:manager/customerCards/addCustomerCard";
            this.command = new GetAddCustomerCardCommand();
        }
    },
    GET_ADD_CUSTOMER_CARD_CASHIER {
        {
            this.key = "GET:cashier/customerCards/addCustomerCard";
            this.command = new GetAddCustomerCardCommand();
        }
    },
    POST_ADD_CUSTOMER_CARD {
        {
            this.key = "POST:manager/customerCards/addCustomerCard";
            this.command = new PostAddCustomerCardCommand(Customer_cardService.getInstance());
        }
    },
    POST_ADD_CUSTOMER_CARD_CASHIER {
        {
            this.key = "POST:cashier/customerCards/addCustomerCard";
            this.command = new PostAddCustomerCardCommand(Customer_cardService.getInstance());
        }
    },
    GET_UPDATE_CUSTOMER_CARD {
        {
            this.key = "GET:manager/customerCards/updateCustomerCard";
            this.command = new GetUpdateCustomerCardCommand(Customer_cardService.getInstance());
        }
    },
    GET_UPDATE_CUSTOMER_CARD_CASHIER {
        {
            this.key = "GET:cashier/customerCards/updateCustomerCard";
            this.command = new GetUpdateCustomerCardCommand(Customer_cardService.getInstance());
        }
    },
    POST_UPDATE_CUSTOMER_CARD {
        {
            this.key = "POST:manager/customerCards/updateCustomerCard";
            this.command = new PostUpdateCustomerCardCommand(Customer_cardService.getInstance());
        }
    },
    POST_UPDATE_CUSTOMER_CARD_CASHIER {
        {
            this.key = "POST:cashier/customerCards/updateCustomerCard";
            this.command = new PostUpdateCustomerCardCommand(Customer_cardService.getInstance());
        }
    },

    // Products Commands
    ALL_PRODUCTS {
        {
            this.key = "GET:manager/products";
            this.command = new AllProductsCommand(ProductService.getInstance(), CategoryService.getInstance());
        }
    },
    ALL_PRODUCTS_CASHIER {
        {
            this.key = "GET:cashier/products";
            this.command = new AllProductsCommand(ProductService.getInstance(), CategoryService.getInstance());
        }
    },
    DELETE_PRODUCT {
        {
            this.key = "GET:manager/products/deleteProduct";
            this.command = new DeleteProductCommand(ProductService.getInstance());
        }
    },
    GET_ADD_PRODUCT {
        {
            this.key = "GET:manager/products/addProduct";
            this.command = new GetAddProductCommand();
        }
    },
    POST_ADD_PRODUCT {
        {
            this.key = "POST:manager/products/addProduct";
            this.command = new PostAddProductCommand(ProductService.getInstance(), CategoryService.getInstance());
        }
    },
    GET_UPDATE_PRODUCT {
        {
            this.key = "GET:manager/products/updateProduct";
            this.command = new GetUpdateProductCommand(ProductService.getInstance(), CategoryService.getInstance());
        }
    },
    POST_UPDATE_PRODUCT {
        {
            this.key = "POST:manager/products/updateProduct";
            this.command = new PostUpdateProductCommand(ProductService.getInstance(), CategoryService.getInstance());
        }
    },
    SEARCH_PRODUCTS_BY_CATEGORY_AND_SORT_BY_NAME {
        {
            this.key = "POST:manager/products/searchByCategory";
            this.command = new SearchProductsByCategoryAndSortByNameCommand(ProductService.getInstance(), CategoryService.getInstance());
        }
    },
    SEARCH_PRODUCTS_BY_CATEGORY_AND_SORT_BY_NAME_CASHIER {
        {
            this.key = "POST:cashier/products/searchByCategory";
            this.command = new SearchProductsByCategoryAndSortByNameCommand(ProductService.getInstance(), CategoryService.getInstance());
        }
    },
    SEARCH_PRODUCTS_BY_NAME_AND_SORT {
        {
            this.key = "POST:manager/products/searchByName";
            this.command = new SearchProductsByNameAndSortCommand(ProductService.getInstance(), CategoryService.getInstance());
        }
    },
    SEARCH_PRODUCTS_BY_NAME_AND_SORT_CASHIER {
        {
            this.key = "POST:cashier/products/searchByName";
            this.command = new SearchProductsByNameAndSortCommand(ProductService.getInstance(), CategoryService.getInstance());
        }
    },
    SEARCH_PRODUCTS_BY_NAME {
        {
            this.key = "POST:manager/products/searchByNameOnly";
            this.command = new SearchProductsByNameCommand(ProductService.getInstance(), CategoryService.getInstance());
        }
    },
    SEARCH_PRODUCTS_BY_NAME_CASHIER {
        {
            this.key = "POST:cashier/products/searchByNameOnly";
            this.command = new SearchProductsByNameCommand(ProductService.getInstance(), CategoryService.getInstance());
        }
    },

    // Store Products Commands
    ALL_STORE_PRODUCTS {
        {
            this.key = "GET:manager/storeProducts";
            this.command = new AllStoreProductCommand(Store_productService.getInstance(), ProductService.getInstance());
        }
    },
    ALL_STORE_PRODUCTS_CASHIER {
        {
            this.key = "GET:cashier/storeProducts";
            this.command = new AllStoreProductCommand(Store_productService.getInstance(), ProductService.getInstance());
        }
    },
    DELETE_STORE_PRODUCT {
        {
            this.key = "GET:manager/storeProducts/deleteStoreProduct";
            this.command = new DeleteStoreProductCommand(Store_productService.getInstance());
        }
    },
    GET_ADD_STORE_PRODUCT {
        {
            this.key = "GET:manager/storeProducts/addStoreProduct";
            this.command = new GetAddStoreProductCommand(Store_productService.getInstance());
        }
    },
    POST_ADD_STORE_PRODUCT {
        {
            this.key = "POST:manager/storeProducts/addStoreProduct";
            this.command = new PostAddStoreProductCommand(Store_productService.getInstance(), ProductService.getInstance());
        }
    },
    GET_UPDATE_STORE_PRODUCT {
        {
            this.key = "GET:manager/storeProducts/updateStoreProduct";
            this.command = new GetUpdateStoreProductCommand(Store_productService.getInstance(), ProductService.getInstance());
        }
    },
    POST_UPDATE_STORE_PRODUCT {
        {
            this.key = "POST:manager/storeProducts/updateStoreProduct";
            this.command = new PostUpdateStoreProductCommand(Store_productService.getInstance());
        }
    },
    SEARCH_CATEGORIES_WITH_ALL_PRODUCTS_IN_STORE_PRODUCT {
        {
            this.key = "POST:manager/storeProducts/searchCategoriesWithAllProducts";
            this.command = new SearchCategoriesWithAllProductsInStoreProductCommand(CategoryService.getInstance());
        }
    },
    // Additional Store Products Commands
    SEARCH_AMOUNT_OF_STORE_PRODUCT_PER_PERIOD {
        {
            this.key = "POST:manager/storeProducts/searchAmountPerPeriod";
            this.command = new SearchAmountOfStoreProductPerPeriodCommand(Store_productService.getInstance());
        }
    },
    SEARCH_NON_PROMO_STORE_PRODUCTS_SORTED_BY_NAME {
        {
            this.key = "POST:manager/storeProducts/searchNonPromoByName";
            this.command = new SearchNonPromoStoreProductsSortedByNameCommand(Store_productService.getInstance());
        }
    },
    SEARCH_NON_PROMO_STORE_PRODUCTS_SORTED_BY_NAME_CASHIER {
        {
            this.key = "POST:cashier/storeProducts/searchNonPromoByName";
            this.command = new SearchNonPromoStoreProductsSortedByNameCommand(Store_productService.getInstance());
        }
    },
    SEARCH_NON_PROMO_STORE_PRODUCTS_SORTED_BY_PRODUCT_NUM {
        {
            this.key = "POST:manager/storeProducts/searchNonPromoByProductNum";
            this.command = new SearchNonPromoStoreProductsSortedByProductNumCommand(Store_productService.getInstance());
        }
    },
    SEARCH_NON_PROMO_STORE_PRODUCTS_SORTED_BY_PRODUCT_NUM_CASHIER {
        {
            this.key = "POST:cashier/storeProducts/searchNonPromoByProductNum";
            this.command = new SearchNonPromoStoreProductsSortedByProductNumCommand(Store_productService.getInstance());
        }
    },
    SEARCH_PROMO_STORE_PRODUCTS_SORTED_BY_NAME {
        {
            this.key = "POST:manager/storeProducts/searchPromoByName";
            this.command = new SearchPromoStoreProductsSortedByNameCommand(Store_productService.getInstance());
        }
    },
    SEARCH_PROMO_STORE_PRODUCTS_SORTED_BY_NAME_CASHIER {
        {
            this.key = "POST:cashier/storeProducts/searchPromoByName";
            this.command = new SearchPromoStoreProductsSortedByNameCommand(Store_productService.getInstance());
        }
    },
    SEARCH_PROMO_STORE_PRODUCTS_SORTED_BY_PRODUCT_NUM {
        {
            this.key = "POST:manager/storeProducts/searchPromoByProductNum";
            this.command = new SearchPromoStoreProductsSortedByProductNumCommand(Store_productService.getInstance());
        }
    },
    SEARCH_PROMO_STORE_PRODUCTS_SORTED_BY_PRODUCT_NUM_CASHIER {
        {
            this.key = "POST:cashier/storeProducts/searchPromoByProductNum";
            this.command = new SearchPromoStoreProductsSortedByProductNumCommand(Store_productService.getInstance());
        }
    },
    SEARCH_STORE_PRODUCT_DETAILS_BY_UPC {
        {
            this.key = "POST:manager/storeProducts/searchDetailsByUpc";
            this.command = new SearchStoreProductDetailsByUpcCommand(Store_productService.getInstance());
        }
    },
    SEARCH_STORE_PRODUCT_DETAILS_BY_UPC_CASHIER {
        {
            this.key = "POST:cashier/storeProducts/searchDetailsByUpc";
            this.command = new SearchStoreProductDetailsByUpcCommand(Store_productService.getInstance());
        }
    },
    SEARCH_STORE_PRODUCTS_SORTED_BY_NAME {
        {
            this.key = "POST:manager/storeProducts/searchSortedByName";
            this.command = new SearchStoreProductsSortedByNameCommand(Store_productService.getInstance());
        }
    },
    SEARCH_STORE_PRODUCTS_SORTED_BY_NAME_CASHIER {
        {
            this.key = "POST:cashier/storeProducts/searchSortedByName";
            this.command = new SearchStoreProductsSortedByNameCommand(Store_productService.getInstance());
        }
    },
    SEARCH_STORE_PRODUCTS_SORTED_BY_PRODUCTS_NUM {
        {
            this.key = "POST:manager/storeProducts/searchSortedByProductsNum";
            this.command = new SearchStoreProductsSortedByProductsNumCommand(Store_productService.getInstance());
        }
    },
    SEARCH_STORE_PRODUCTS_SORTED_BY_PRODUCTS_NUM_CASHIER {
        {
            this.key = "POST:cashier/storeProducts/searchSortedByProductsNum";
            this.command = new SearchStoreProductsSortedByProductsNumCommand(Store_productService.getInstance());
        }
    },

    // Additional Checks Commands
    SEARCH_CHECK_BY_EMPLOYEE_ID {
        {
            this.key = "POST:manager/checks/searchByEmployeeId";
            this.command = new SearchCheckByEmployeeIdCommand(CheckService.getInstance());
        }
    },
    SEARCH_CHECK_BY_EMPLOYEE_ID_CASHIER {
        {
            this.key = "POST:cashier/checks/searchByEmployeeId";
            this.command = new SearchCheckByEmployeeIdCommand(CheckService.getInstance());
        }
    },
    SEARCH_CHECK_BY_NUMBER {
        {
            this.key = "POST:manager/checks/searchByNumber";
            this.command = new SearchCheckByNumberCommand(CheckService.getInstance());
        }
    },
    SEARCH_CHECK_BY_NUMBER_CASHIER {
        {
            this.key = "POST:cashier/checks/searchByNumber";
            this.command = new SearchCheckByNumberCommand(CheckService.getInstance());
        }
    },
    SEARCH_CHECKS_BY_EMPLOYEE_ID_PER_PERIOD {
        {
            this.key = "POST:manager/checks/searchByEmployeeIdPerPeriod";
            this.command = new SearchChecksByEmployeeIdPerPeriodCommand(CheckService.getInstance());
        }
    },
    SEARCH_CHECKS_BY_EMPLOYEE_ID_PER_PERIOD_CASHIER {
        {
            this.key = "POST:cashier/checks/searchByEmployeeIdPerPeriod";
            this.command = new SearchChecksByEmployeeIdPerPeriodCommand(CheckService.getInstance());
        }
    },
    SEARCH_CHECKS_BY_EMPLOYEE_SURNAME {
        {
            this.key = "POST:manager/checks/searchByEmployeeSurname";
            this.command = new SearchChecksByEmployeeSurnameCommand(CheckService.getInstance());
        }
    },
    SEARCH_CHECKS_BY_EMPLOYEE_SURNAME_PER_PERIOD {
        {
            this.key = "POST:manager/checks/searchByEmployeeSurnamePerPeriod";
            this.command = new SearchChecksByEmployeeSurnamePerPeriodCommand(CheckService.getInstance());
        }
    },
    SEARCH_CHECKS_PER_PERIOD {
        {
            this.key = "POST:manager/checks/searchPerPeriod";
            this.command = new SearchChecksPerPeriodCommand(CheckService.getInstance());
        }
    },
    SEARCH_SUM_OF_CHECKS_BY_EMPLOYEE_SURNAME_PER_PERIOD {
        {
            this.key = "POST:manager/checks/searchSumByEmployeeSurnamePerPeriod";
            this.command = new SearchSumOfChecksByEmployeeSurnamePerPeriodCommand(CheckService.getInstance());
        }
    },
    SEARCH_SUM_OF_CHECKS_PER_PERIOD {
        {
            this.key = "POST:manager/checks/searchSumPerPeriod";
            this.command = new SearchSumOfChecksPerPeriodCommand(CheckService.getInstance());
        }
    },

    // Additional Customer Cards Commands
    SEARCH_CUSTOMER_CARD_BY_PERCENT_SORTED_BY_SURNAME {
        {
            this.key = "POST:manager/customerCards/searchByPercentSortedBySurname";
            this.command = new SearchCustomerCardByPercentSortedBySurnameCommand(Customer_cardService.getInstance());
        }
    },
    SEARCH_CUSTOMER_CARD_BY_SURNAME {
        {
            this.key = "POST:manager/customerCards/searchBySurname";
            this.command = new SearchCustomerCardBySurnameCommand(Customer_cardService.getInstance());
        }
    },
    SEARCH_CUSTOMER_CARD_BY_SURNAME_CASHIER {
        {
            this.key = "POST:cashier/customerCards/searchBySurname";
            this.command = new SearchCustomerCardBySurnameCommand(Customer_cardService.getInstance());
        }
    },
    SEARCH_CUSTOMER_CARD_DISCOUNT_FOR_EACH_CATEGORY {
        {
            this.key = "POST:manager/customerCards/searchDiscountForEachCategory";
            this.command = new SearchCustomerCardDiscountForEachCategoryCommand(Customer_cardService.getInstance());
        }
    };

    String key;
    Command command;

    public Command getCommand() {
        return command;
    }

    public String getKey() {
        return key;
    }

    public static Command getCommand(String key) {
        for (final CommandEnum command : CommandEnum.values()) {
            if (command.getKey().equals(key)) {
                return command.getCommand();
            }
        }
        return PAGE_NOT_FOUND.getCommand();
    }
}
