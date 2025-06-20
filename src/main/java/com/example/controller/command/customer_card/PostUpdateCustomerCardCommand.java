package com.example.controller.command.customer_card;

import com.example.constants.Attribute;
import com.example.constants.ServletPath;
import com.example.controller.command.Command;
import com.example.controller.utils.HttpWrapper;
import com.example.controller.utils.RedirectionManager;
import com.example.entity.Customer_card;
import com.example.locale.Message;
import com.example.service.Customer_cardService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PostUpdateCustomerCardCommand implements Command {

    private final Customer_cardService customerCardService;

    public PostUpdateCustomerCardCommand(Customer_cardService customerCardService) {
        this.customerCardService = customerCardService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Customer_card customerCard = getUserInput(request);
        customerCardService.createCustomer_card(customerCard);
        redirectToAllCustomerCardsPageWithSuccessMessage(request, response);
        return RedirectionManager.REDIRECTION;
    }

    private Customer_card getUserInput(HttpServletRequest request) {
        return new Customer_card.Builder().setNumber(request.getParameter(Attribute.CARD_NUMBER))
                .setName(request.getParameter(Attribute.CUST_NAME))
                .setSurname(request.getParameter(Attribute.SURNAME)).setPatronymic(request.getParameter(Attribute.CUST_PATRONYMIC))
                .setPhone(request.getParameter(Attribute.PHONE_NUMBER))
                .setCity(request.getParameter(Attribute.CITY)).setStreet(request.getParameter(Attribute.STREET))
                .setZip_code(request.getParameter(Attribute.ZIP_CODE)).setPercent(Long.valueOf(request.getParameter(Attribute.PERCENT)))
                .build();
    }

    private void redirectToAllCustomerCardsPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_CUSTOMER_CARD_UPDATE);
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_CUSTOMER_CARDS, urlParams);
    }

}