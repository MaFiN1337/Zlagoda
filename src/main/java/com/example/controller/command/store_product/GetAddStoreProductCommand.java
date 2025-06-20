package com.example.controller.command.store_product;

import com.example.constants.Attribute;
import com.example.constants.Page;
import com.example.controller.command.Command;
import com.example.service.Store_productService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetAddStoreProductCommand implements Command {

    private final Store_productService storeProductService;

    public GetAddStoreProductCommand(Store_productService storeProductService) {
        this.storeProductService = storeProductService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(Attribute.STORE_PRODUCTS, storeProductService.getAllStore_products());
        return Page.ADD_UPDATE_STORE_PRODUCT_VIEW;
    }
}