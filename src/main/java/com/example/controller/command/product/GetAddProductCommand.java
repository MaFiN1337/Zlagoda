package com.example.controller.command.product;

import com.example.constants.Attribute;
import com.example.controller.command.Command;
import com.example.service.CategoryService;
import com.example.constants.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetAddProductCommand implements Command {

    private final CategoryService categoryService;

    public GetAddProductCommand(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(Attribute.CATEGORIES, categoryService.getAllCategories());
        return Page.ADD_UPDATE_PRODUCT_VIEW;
    }
}
