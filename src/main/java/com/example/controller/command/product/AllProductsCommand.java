package com.example.controller.command.product;

import com.example.controller.command.Command;
import com.example.entity.Category;
import com.example.entity.Product;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import com.example.constants.Attribute;
import com.example.constants.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllProductsCommand implements Command {

    private final ProductService productService;
    private final CategoryService categoryService;

    public AllProductsCommand(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> products = productService.getAllProducts();
        List<Category> categories = categoryService.getAllCategories();

        request.setAttribute(Attribute.PRODUCTS, products);
        request.setAttribute(Attribute.CATEGORIES, categories);
        return Page.ALL_PRODUCTS_VIEW;
    }
}
