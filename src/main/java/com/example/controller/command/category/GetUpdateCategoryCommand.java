package com.example.controller.command.category;

import com.example.constants.Page;
import com.example.controller.command.Command;
import com.example.entity.Category;
import com.example.service.CategoryService;
import com.example.constants.Attribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class GetUpdateCategoryCommand implements Command {

    private final CategoryService categoryService;

    public GetUpdateCategoryCommand(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long categoryId = Long.parseLong(request.getParameter(Attribute.CATEGORY_NUMBER));

        Optional<Category> category = categoryService.getCategoryById(categoryId);
        request.setAttribute(Attribute.CATEGORY, category.get());

        return Page.ADD_UPDATE_CATEGORY_VIEW;
    }
}
