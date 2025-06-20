package com.example.controller.command.product;

import com.example.controller.command.Command;
import com.example.entity.Product;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import com.example.constants.Attribute;
import com.example.constants.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class GetUpdateProductCommand implements Command {

    private final ProductService productService;
    private final CategoryService categoryService;

    public GetUpdateProductCommand(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long productId = Long.parseLong(request.getParameter(Attribute.ID_PRODUCT));

        Optional<Product> product = productService.getProductById(productId);

        request.setAttribute(Attribute.CATEGORIES, categoryService.getAllCategories());
        request.setAttribute(Attribute.PRODUCT, product.get());
        return Page.ADD_UPDATE_PRODUCT_VIEW;
    }
}
