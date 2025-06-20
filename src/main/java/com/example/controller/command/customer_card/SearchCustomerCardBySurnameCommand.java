package com.example.controller.command.customer_card;

import com.example.constants.Attribute;
import com.example.constants.Page;
import com.example.constants.ServletPath;
import com.example.controller.command.Command;
import com.example.controller.utils.HttpWrapper;
import com.example.controller.utils.RedirectionManager;
import com.example.entity.Customer_card;
import com.example.locale.Message;
import com.example.service.Customer_cardService;
import com.example.validator.field.AbstractFieldValidatorHandler;
import com.example.validator.field.FieldValidatorKey;
import com.example.validator.field.FieldValidatorsChainGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchCustomerCardBySurnameCommand implements Command {

    private final Customer_cardService customerCardService;

    public SearchCustomerCardBySurnameCommand(Customer_cardService customerCardService) {
        this.customerCardService = customerCardService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String surname = request.getParameter(Attribute.CUST_SURNAME);
        List<String> errors = validateUserInput(surname);
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams;

        String uri = request.getRequestURI();
        String afterController = uri.substring(uri.indexOf("/controller/") + "/controller/".length());
        String firstSegment = afterController.contains("/")
                ? afterController.substring(0, afterController.indexOf("/"))
                : afterController;

        if (!errors.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, errors.get(0));
            if (firstSegment.equals("manager")){
                RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.MANAGER_CUSTOMER_CARDS, urlParams);
            }
            else {
                RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.CASHIER_CUSTOMER_CARDS, urlParams);
            }             return RedirectionManager.REDIRECTION;
        }

        List<Customer_card> customer_cards = customerCardService.searchCustomer_cardBySurname(surname);

        if (customer_cards.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, Message.CUSTOMER_CARD_IS_NOT_FOUND);
            if (firstSegment.equals("manager")){
                RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.MANAGER_PRODUCTS, urlParams);
            }
            else {
                RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.CASHIER_PRODUCTS, urlParams);
            }             return RedirectionManager.REDIRECTION;
        }

        request.setAttribute(Attribute.CUSTOMER_CARDS, customer_cards);
        return Page.ALL_CUSTOMER_CARDS_VIEW;
    }

    private List<String> validateUserInput(String surname) {
        List<String> errors = new ArrayList<>();

        AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();
        fieldValidator.validateField(FieldValidatorKey.SURNAME, surname, errors);
        return errors;
    }
}