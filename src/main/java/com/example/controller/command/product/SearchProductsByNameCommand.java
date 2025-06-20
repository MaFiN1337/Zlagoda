package com.example.controller.command.product;

import com.example.constants.Attribute;
import com.example.constants.Page;
import com.example.constants.ServletPath;
import com.example.controller.command.Command;
import com.example.controller.utils.HttpWrapper;
import com.example.controller.utils.RedirectionManager;
import com.example.entity.Product;
import com.example.locale.Message;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import com.example.validator.field.AbstractFieldValidatorHandler;
import com.example.validator.field.FieldValidatorKey;
import com.example.validator.field.FieldValidatorsChainGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchProductsByNameCommand implements Command {

    private final ProductService productService;
    private final CategoryService categoryService;

    public SearchProductsByNameCommand(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter(Attribute.PRODUCT_NAME);
        List<String> errors = validateUserInput(name);
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams;
        String uri = request.getRequestURI();
        String afterController = uri.substring(uri.indexOf("/controller/") + "/controller/".length());
        String firstSegment = afterController.contains("/")
                ? afterController.substring(0, afterController.indexOf("/"))
                : afterController;

        if (!errors.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, errors.get(0));
            if (firstSegment.equals("manager")){
                RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.MANAGER_PRODUCTS, urlParams);
            }
            else {
                RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.CASHIER_PRODUCTS, urlParams);
            }            return RedirectionManager.REDIRECTION;
        }

        List<Product> products= productService.searchProductsByName(name);

        if (products.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, Message.PRODUCT_IS_NOT_FOUND);
            if (firstSegment.equals("manager")){
                RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.MANAGER_PRODUCTS, urlParams);
            }
            else {
                RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.CASHIER_PRODUCTS, urlParams);
            }
            return RedirectionManager.REDIRECTION;
        }

        request.setAttribute(Attribute.CATEGORIES, categoryService.getAllCategories());
        request.setAttribute(Attribute.PRODUCTS, products);
        return Page.ALL_PRODUCTS_VIEW;
    }

    private List<String> validateUserInput(String name) {
        List<String> errors = new ArrayList<>();

        AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();
        fieldValidator.validateField(FieldValidatorKey.NAME, name, errors);
        return errors;
    }
}
