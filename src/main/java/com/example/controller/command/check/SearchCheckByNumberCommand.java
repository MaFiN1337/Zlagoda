package com.example.controller.command.check;

import com.example.constants.Attribute;
import com.example.constants.Page;
import com.example.constants.ServletPath;
import com.example.controller.command.Command;
import com.example.controller.utils.HttpWrapper;
import com.example.controller.utils.RedirectionManager;
import com.example.entity.Check;
import com.example.locale.Message;
import com.example.service.CheckService;
import com.example.service.Customer_cardService;
import com.example.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SearchCheckByNumberCommand implements Command {

    private final CheckService checkService;
    private final Customer_cardService customerCardService;
    private final EmployeeService employeeService;

    public SearchCheckByNumberCommand(CheckService checkService, Customer_cardService customerCardService, EmployeeService employeeService) {
        this.checkService = checkService;
        this.customerCardService = customerCardService;
        this.employeeService = employeeService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String check_number = request.getParameter(Attribute.CHECK_NUMBER);
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams;
        Optional<Check> check = checkService.searchCheckByNumber(check_number);

        if (!check.isPresent()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, Message.CHECK_IS_NOT_FOUND);
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_CHECKS, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        request.setAttribute(Attribute.CHECK, check);
        request.setAttribute(Attribute.CUSTOMER_CARDS, customerCardService.getAllCustomer_cards());
        request.setAttribute(Attribute.EMPLOYEES, employeeService.getAllEmployees());
        return Page.ALL_CHECKS_VIEW;
    }
}