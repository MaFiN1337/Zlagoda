package com.example.controller.command.product;

import com.example.constants.Attribute;
import com.example.controller.command.Command;
import com.example.controller.utils.RedirectionManager;
import com.example.entity.Category;
import com.example.entity.Product;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import com.example.constants.Page;
import com.example.locale.Message;
import com.example.constants.ServletPath;
import com.example.validator.entity.ProductValidator;
import com.example.controller.utils.HttpWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostUpdateProductCommand implements Command {

    private final ProductService productService;
    private final CategoryService categoryService;

    public PostUpdateProductCommand(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Product product = getUserInput(request);
        List<String> errors = validateUserInput(product);

        if (errors.isEmpty()) {
            productService.updateProduct(product);
            redirectToAllDishesPageWithSuccessMessage(request, response);
            return RedirectionManager.REDIRECTION;
        }

        addRequestAttributes(request, product, errors);
        return Page.ADD_UPDATE_PRODUCT_VIEW;
    }

    private Product getUserInput(HttpServletRequest request) {
        return new Product.Builder().setId(Long.parseLong(request.getParameter(Attribute.ID_PRODUCT)))
                .setName(request.getParameter(Attribute.PRODUCT_NAME))
                .setCharacteristics(request.getParameter(Attribute.CHARACTERISTICS))
                .setCategory(
                        new Category.Builder().setId(Long.parseLong(request.getParameter(Attribute.CATEGORY))).build())
                .build();
    }

    private List<String> validateUserInput(Product product) {
        return ProductValidator.getInstance().validate(product);
    }

    private void redirectToAllDishesPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_PRODUCT_UPDATE);
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_PRODUCTS, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, Product product, List<String> errors) {
        request.setAttribute(Attribute.CATEGORIES, categoryService.getAllCategories());
        request.setAttribute(Attribute.PRODUCT, product);
        request.setAttribute(Attribute.ERRORS, errors);
    }
}
