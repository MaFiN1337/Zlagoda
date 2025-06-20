package com.example.controller.command.store_product;

import com.example.constants.Attribute;
import com.example.constants.ServletPath;
import com.example.controller.command.Command;
import com.example.controller.utils.HttpWrapper;
import com.example.controller.utils.RedirectionManager;
import com.example.entity.Product;
import com.example.entity.Store_product;
import com.example.locale.Message;
import com.example.service.ProductService;
import com.example.service.Store_productService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PostAddStoreProductCommand implements Command {

    private final Store_productService storeProductService;
    private final ProductService productService;

    public PostAddStoreProductCommand(Store_productService storeProductService, ProductService productService) {
        this.storeProductService = storeProductService;
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Store_product storeProduct = getUserInput(request);
        storeProductService.createStore_product(storeProduct);
        redirectToAllStore_productsPageWithSuccessMessage(request, response);
        return RedirectionManager.REDIRECTION;
    }

    private Store_product getUserInput(HttpServletRequest request) {
        return new Store_product.Builder().setProducts_number(Long.valueOf(request.getParameter(Attribute.PRODUCTS_NUMBER)))
                .setSelling_price(BigDecimal.valueOf(Long.parseLong(request.getParameter(Attribute.SELLING_PRICE))))
                .setUpc_prom(null)
                .setPromotional_product(Boolean.parseBoolean(request.getParameter(Attribute.PROMO_PRODUCT)))
                .setProduct(
                        new Product.Builder().setId(Long.valueOf(request.getParameter(Attribute.ID_PRODUCT))).build()
                ).build();
    }


    private void redirectToAllStore_productsPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_STORE_PRODUCT_ADDITION);
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.MANAGER_STORE_PRODUCTS, urlParams);
    }
}