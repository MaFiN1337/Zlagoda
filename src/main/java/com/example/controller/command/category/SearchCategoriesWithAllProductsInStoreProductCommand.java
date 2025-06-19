package com.example.controller.command.category;

import com.example.constants.Attribute;
import com.example.constants.Page;
import com.example.controller.command.Command;
import com.example.entity.Category;
import com.example.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchCategoriesWithAllProductsInStoreProductCommand implements Command {

    private final CategoryService categoryService;

    public SearchCategoriesWithAllProductsInStoreProductCommand(CategoryService userService) {
        this.categoryService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Category> categories = categoryService.searchCategoriesWithAllProductsInStore_product();

        request.setAttribute(Attribute.CATEGORIES, categories);
        return Page.ALL_CATEGORIES_VIEW;
    }
}
