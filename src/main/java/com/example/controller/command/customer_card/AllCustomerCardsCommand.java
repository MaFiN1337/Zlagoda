package com.example.controller.command.customer_card;

import com.example.constants.Attribute;
import com.example.constants.Page;
import com.example.controller.command.Command;
import com.example.entity.Customer_card;
import com.example.service.Customer_cardService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllCustomerCardsCommand implements Command {

    private final Customer_cardService customerCardService;

    public AllCustomerCardsCommand(Customer_cardService customerCardService) {
        this.customerCardService = customerCardService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Customer_card> customer_cards = customerCardService.getAllCustomer_cards();

        request.setAttribute(Attribute.CUSTOMER_CARDS, customer_cards);
        return Page.ALL_CUSTOMER_CARDS_VIEW;
    }
}