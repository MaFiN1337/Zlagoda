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
    private final ProductService productService;

    public AllStoreProductCommand(Store_productService storeProductService, ProductService productService) {
        this.storeProductService = storeProductService;
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Store_product> storeProducts = storeProductService.getAllStore_products();
        List<Product> products = productService.getAllProducts();

        request.setAttribute(Attribute.STORE_PRODUCTS, storeProducts);
        request.setAttribute(Attribute.PRODUCTS, products);

        return Page.ALL_STORE_PRODUCTS_VIEW;
    }
}