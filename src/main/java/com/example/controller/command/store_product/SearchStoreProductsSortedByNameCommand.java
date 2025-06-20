package com.example.controller.command.store_product;

import com.example.constants.Attribute;
import com.example.constants.Page;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchStoreProductsSortedByNameCommand implements Command {

    private final Store_productService storeProductService;
    private final ProductService productService;

    public SearchStoreProductsSortedByNameCommand(Store_productService storeProductService, ProductService productService) {
        this.storeProductService = storeProductService;
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams;

        List<Store_product> storeProducts = storeProductService.searchStore_productSortedByName();
        List<Product> products = productService.getAllProducts();



        if (storeProducts.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, Message.STORE_PRODUCT_IS_NOT_FOUND);
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_STORE_PRODUCTS, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        request.setAttribute(Attribute.STORE_PRODUCTS, storeProducts);
        request.setAttribute(Attribute.PRODUCTS, products);
        return Page.ALL_STORE_PRODUCTS_VIEW;
    }
}