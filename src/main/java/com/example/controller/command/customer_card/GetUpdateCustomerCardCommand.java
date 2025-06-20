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
import java.util.Optional;

public class GetUpdateCustomerCardCommand implements Command {

    private final Customer_cardService customerCardService;

    public GetUpdateCustomerCardCommand(Customer_cardService customerCardService) {
        this.customerCardService = customerCardService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String card_number = request.getParameter(Attribute.CARD_NUMBER);

        Optional<Customer_card> customer_card = customerCardService.getCustomer_cardById(card_number);
        request.setAttribute(Attribute.CUSTOMER_CARD, customer_card.get());

        return Page.ADD_UPDATE_CUSTOMER_CARD_VIEW;
    }
}
