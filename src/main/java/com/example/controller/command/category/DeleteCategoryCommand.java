package com.example.controller.command.category;

import com.example.constants.Attribute;
import com.example.controller.command.Command;
import com.example.controller.utils.RedirectionManager;
import com.example.service.CategoryService;
import com.example.controller.utils.HttpWrapper;
import com.example.locale.Message;
import com.example.constants.ServletPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteCategoryCommand implements Command {
    private final CategoryService categoryService;

    public DeleteCategoryCommand(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long categoryNumber = Long.parseLong(request.getParameter(Attribute.CATEGORY_NUMBER));

        categoryService.deleteCategory(categoryNumber);

        redirectToAllCategoriesPageWithSuccessMessage(request, response);
        return RedirectionManager.REDIRECTION;
    }

    private void redirectToAllCategoriesPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_CATEGORY_DELETE);
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.MANAGER_CATEGORIES, urlParams);
    }
}
