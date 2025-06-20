package com.example.controller.command.customer_card;

import com.example.constants.Attribute;
import com.example.constants.Page;
import com.example.constants.ServletPath;
import com.example.controller.command.Command;
import com.example.controller.utils.HttpWrapper;
import com.example.controller.utils.RedirectionManager;
import com.example.dto.Customer_cardDiscountDto;
import com.example.entity.Customer_card;
import com.example.locale.Message;
import com.example.service.Customer_cardService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchCustomerCardDiscountForEachCategoryCommand implements Command {

    private final Customer_cardService customerCardService;

    public SearchCustomerCardDiscountForEachCategoryCommand(Customer_cardService customerCardService) {
        this.customerCardService = customerCardService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cust_surname = request.getParameter(Attribute.CUST_SURNAME);
        String card_number = request.getParameter(Attribute.CARD_NUMBER);
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams;

        List<Customer_cardDiscountDto> discounts = customerCardService
                .searchCustomer_cardDiscountForEachCategory(
                        new Customer_card.Builder().setNumber(card_number).setSurname(cust_surname).build()
                );

        if (discounts.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, Message.DISCOUNT_IS_NOT_FOUND);
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.MANAGER_CUSTOMER_CARDS, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        request.setAttribute(Attribute.DISCOUNTS, discounts);
        return Page.ALL_DISCOUNTS_VIEW;
    }

}