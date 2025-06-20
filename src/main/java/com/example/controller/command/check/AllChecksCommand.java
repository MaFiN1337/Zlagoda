package com.example.controller.command.check;

import com.example.constants.Attribute;
import com.example.constants.Page;
import com.example.controller.command.Command;
import com.example.entity.*;
import com.example.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class AllChecksCommand implements Command {

    private final CheckService checkService;
    private final Customer_cardService customerCardService;
    private final EmployeeService employeeService;

    public AllChecksCommand(CheckService checkService, Customer_cardService customerCardService, EmployeeService employeeService) {
        this.checkService = checkService;
        this.customerCardService = customerCardService;
        this.employeeService = employeeService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Check> checks = checkService.getAllChecks();
        List<Customer_card> customer_cards = customerCardService.getAllCustomer_cards();
        List<Employee> employees = employeeService.getAllEmployees();

        request.setAttribute(Attribute.CUSTOMER_CARDS, customer_cards);
        request.setAttribute(Attribute.CHECKS, checks);
        request.setAttribute(Attribute.EMPLOYEES, employees);

        return Page.ALL_CHECKS_VIEW;
    }
}