package com.example.controller.command.store_product;

import com.example.constants.Attribute;
import com.example.constants.Page;
import com.example.controller.command.Command;
import com.example.entity.Store_product;
import com.example.service.ProductService;
import com.example.service.Store_productService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class GetUpdateStoreProductCommand implements Command {

    private final Store_productService storeProductService;
    private final ProductService productService;

    public GetUpdateStoreProductCommand(Store_productService storeProductService, ProductService productService) {
        this.storeProductService = storeProductService;
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String UPC = request.getParameter(Attribute.UPC);

        Optional<Store_product> storeProduct = storeProductService.getStore_productById(UPC);

        request.setAttribute(Attribute.PRODUCTS, productService.getAllProducts());
        request.setAttribute(Attribute.STORE_PRODUCT, storeProduct.get());

        return Page.ADD_UPDATE_STORE_PRODUCT_VIEW;
    }
}