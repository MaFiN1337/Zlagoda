package com.example.controller.command.customer_card;

import com.example.constants.Attribute;
import com.example.constants.ServletPath;
import com.example.controller.command.Command;
import com.example.controller.utils.HttpWrapper;
import com.example.controller.utils.RedirectionManager;
import com.example.locale.Message;
import com.example.service.Customer_cardService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteCustomerCardCommand implements Command {
    private final Customer_cardService customerCardService;

    public DeleteCustomerCardCommand(Customer_cardService customerCardService) {
        this.customerCardService = customerCardService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String card_number = request.getParameter(Attribute.CARD_NUMBER);

        customerCardService.deleteCustomer_card(card_number);

        redirectToAllCustomerCardsPageWithSuccessMessage(request, response);
        return RedirectionManager.REDIRECTION;
    }

    private void redirectToAllCustomerCardsPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_CUSTOMER_CARD_DELETE);
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_CUSTOMER_CARDS, urlParams);
    }
}