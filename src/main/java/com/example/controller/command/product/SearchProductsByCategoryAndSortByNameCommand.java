package com.example.controller.command.product;

import com.example.constants.Attribute;
import com.example.controller.command.Command;
import com.example.controller.utils.HttpWrapper;
import com.example.controller.utils.RedirectionManager;
import com.example.entity.Product;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import com.example.constants.ServletPath;
import com.example.locale.Message;
import com.example.constants.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchProductsByCategoryAndSortByNameCommand implements Command {

    private final ProductService productService;
    private final CategoryService categoryService;

    public SearchProductsByCategoryAndSortByNameCommand(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String category = request.getParameter(Attribute.CATEGORY);
        List<String> errors = validateUserInput(category);
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
            }
            return RedirectionManager.REDIRECTION;
        }

        List<Product> products = productService.searchProductsByCategoryAndSortByName(category);

        if (products.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, Message.PRODUCT_IS_NOT_FOUND);
            if (firstSegment.equals("manager")){
                RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.MANAGER_PRODUCTS, urlParams);
            }
            else {
                RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.CASHIER_PRODUCTS, urlParams);
            }            return RedirectionManager.REDIRECTION;
        }

        request.setAttribute(Attribute.CATEGORIES, categoryService.getAllCategories());
        request.setAttribute(Attribute.PRODUCTS, products);
        return Page.ALL_PRODUCTS_VIEW;
    }

    private List<String> validateUserInput(String category) {
        List<String> errors = new ArrayList<>();

        if (category.isEmpty()) {
            errors.add(Message.INVALID_CATEGORY);
        }

        return errors;
    }
}
