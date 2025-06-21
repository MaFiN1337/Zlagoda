package com.example.controller.command.store_product;

import com.example.constants.Attribute;
import com.example.constants.Page;
import com.example.controller.command.Command;
import com.example.entity.*;
import com.example.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllStoreProductCommand implements Command {

    private final Store_productService storeProductService;

    public AllStoreProductCommand(Store_productService storeProductService) {
        this.storeProductService = storeProductService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Store_product> storeProducts = storeProductService.getAllStore_products();
        request.setAttribute(Attribute.STORE_PRODUCTS, storeProducts);

        String uri = request.getRequestURI();
        String afterController = uri.substring(uri.indexOf("/controller/") + "/controller/".length());
        String firstSegment = afterController.contains("/")
                ? afterController.substring(0, afterController.indexOf("/"))
                : afterController;
        if (firstSegment.equals("manager")){
            return Page.ALL_STORE_PRODUCTS_VIEW;        }
        else { return
                Page.ALL_STORE_PRODUCTS_CASHIER_VIEW ;      }
    }
}