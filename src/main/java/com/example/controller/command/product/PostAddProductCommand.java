package com.example.controller.command.product;

import com.example.constants.Attribute;
import com.example.constants.ServletPath;
import com.example.controller.command.Command;
import com.example.controller.utils.HttpWrapper;
import com.example.controller.utils.RedirectionManager;
import com.example.entity.Category;
import com.example.entity.Product;
import com.example.locale.Message;
import com.example.service.CategoryService;
import com.example.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PostAddProductCommand implements Command {

    private final ProductService productService;
    private final CategoryService categoryService;

    public PostAddProductCommand(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Product product = getUserInput(request);
            productService.createProduct(product);
            redirectToAllProductsPageWithSuccessMessage(request, response);
            return RedirectionManager.REDIRECTION;
    }

    private Product getUserInput(HttpServletRequest request) {
        return new Product.Builder().setName(request.getParameter(Attribute.PRODUCT_NAME))
                .setCharacteristics(request.getParameter(Attribute.CHARACTERISTICS))
                .setCategory(
                        new Category.Builder().setId(Long.parseLong(request.getParameter(Attribute.CATEGORY))).build())
                .build();
    }

    private void redirectToAllProductsPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_PRODUCT_ADDITION);
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.MANAGER_PRODUCTS, urlParams);
    }

}
