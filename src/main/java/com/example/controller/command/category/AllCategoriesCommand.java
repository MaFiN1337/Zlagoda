package com.example.controller.command.category;

import com.example.controller.command.Command;
import com.example.service.CategoryService;
import com.example.entity.Category;
import com.example.constants.Attribute;
import com.example.constants.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllCategoriesCommand implements Command{
    private final CategoryService categoryService;

    public AllCategoriesCommand(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute(Attribute.CATEGORIES, categories);
        return Page.ALL_CATEGORIES_VIEW;
    }
}
