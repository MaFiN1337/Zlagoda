package com.example.controller.command.category;

import com.example.constants.Attribute;
import com.example.constants.Page;
import com.example.constants.ServletPath;
import com.example.controller.command.Command;
import com.example.controller.utils.HttpWrapper;
import com.example.controller.utils.RedirectionManager;
import com.example.entity.Category;
import com.example.locale.Message;
import com.example.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostAddCategoryCommand implements Command {

    private final CategoryService categoryService;

    public PostAddCategoryCommand(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Category category = getUserInput(request);
        List<String> errors = validateUserInput(category);

        if (errors.isEmpty()) {
            categoryService.createCategory(category);
            redirectToAllCategoriesPageWithSuccessMessage(request, response);
            return RedirectionManager.REDIRECTION;
        }

        addRequestAttributes(request, category, errors);
        return Page.ADD_UPDATE_CATEGORY_VIEW;
    }

    private Category getUserInput(HttpServletRequest request) {
        return new Category.Builder().setName(request.getParameter(Attribute.CATEGORY_NUMBER)).build();
    }

    private List<String> validateUserInput(Category category) {
        return CategoryValidator.getInstance().validate(category);
    }

    private void redirectToAllCategoriesPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_CATEGORY_ADDITION);
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_CATEGORIES, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, Category category, List<String> errors) {
        request.setAttribute(Attribute.CATEGORY, category);
        request.setAttribute(Attribute.ERRORS, errors);
    }
}
