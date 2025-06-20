package com.example.controller.command.store_product;

import com.example.constants.Attribute;
import com.example.constants.Page;
import com.example.constants.ServletPath;
import com.example.controller.command.Command;
import com.example.controller.utils.HttpWrapper;
import com.example.controller.utils.RedirectionManager;
import com.example.dto.Store_productWithProductDto;
import com.example.locale.Message;
import com.example.service.Store_productService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SearchStoreProductDetailsByUpcCommand implements Command {

    private final Store_productService storeProductService;

    public SearchStoreProductDetailsByUpcCommand(Store_productService storeProductService) {
        this.storeProductService = storeProductService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams;
        String UPC = request.getParameter(Attribute.UPC);
        Optional<Store_productWithProductDto> details = storeProductService.searchStoreProductDetailsByUPC(UPC);
        if (!details.isPresent()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, Message.STORE_PRODUCT_IS_NOT_FOUND);
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_STORE_PRODUCTS, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        request.setAttribute(Attribute.DETAILS, details);
        return Page.ALL_STORE_PRODUCTS_DETAILS_VIEW;
    }
}