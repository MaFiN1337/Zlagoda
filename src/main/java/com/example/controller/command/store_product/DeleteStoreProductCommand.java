package com.example.controller.command.store_product;

import com.example.constants.Attribute;
import com.example.constants.ServletPath;
import com.example.controller.command.Command;
import com.example.controller.utils.HttpWrapper;
import com.example.controller.utils.RedirectionManager;
import com.example.locale.Message;
import com.example.service.Store_productService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteStoreProductCommand implements Command {
    private final Store_productService storeProductService;

    public DeleteStoreProductCommand(Store_productService storeProductService) {
        this.storeProductService = storeProductService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String UPC = request.getParameter(Attribute.UPC);

        storeProductService.deleteStore_product(UPC);

        redirectToAllStore_productsPageWithSuccessMessage(request, response);
        return RedirectionManager.REDIRECTION;
    }

    private void redirectToAllStore_productsPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_STORE_PRODUCT_DELETE);
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_STORE_PRODUCTS, urlParams);
    }
}