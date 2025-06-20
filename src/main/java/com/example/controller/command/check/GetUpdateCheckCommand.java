package com.example.controller.command.check;

import com.example.constants.Attribute;
import com.example.constants.Page;
import com.example.controller.command.Command;
import com.example.entity.Check;
import com.example.service.CheckService;
import com.example.service.Customer_cardService;
import com.example.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class GetUpdateCheckCommand implements Command {

    private final CheckService checkService;
    private final Customer_cardService customerCardService;
    private final EmployeeService employeeService;

    public GetUpdateCheckCommand(CheckService checkService, Customer_cardService customerCardService, EmployeeService employeeService) {
        this.checkService = checkService;
        this.customerCardService = customerCardService;
        this.employeeService = employeeService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String checkNum = request.getParameter(Attribute.CHECK_NUMBER);

        Optional<Check> check = checkService.getCheckById(checkNum);

        request.setAttribute(Attribute.CUSTOMER_CARDS, customerCardService.getAllCustomer_cards());
        request.setAttribute(Attribute.CHECK, check.get());
        request.setAttribute(Attribute.EMPLOYEES, employeeService.getAllEmployees());

        return Page.ADD_UPDATE_CHECK_VIEW;
    }
}