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
import com.example.validator.field.AbstractFieldValidatorHandler;
import com.example.validator.field.FieldValidatorKey;
import com.example.validator.field.FieldValidatorsChainGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchChecksByEmployeeSurnamePerPeriodCommand implements Command {

    private final CheckService checkService;
    private final Customer_cardService customerCardService;
    private final EmployeeService employeeService;

    public SearchChecksByEmployeeSurnamePerPeriodCommand(CheckService checkService, Customer_cardService customerCardService, EmployeeService employeeService) {
        this.checkService = checkService;
        this.customerCardService = customerCardService;
        this.employeeService = employeeService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String empl_surname = request.getParameter(Attribute.EMPLOYEE_SURNAME);
        String fromDate = request.getParameter(Attribute.FROM_DATE);
        String toDate = request.getParameter(Attribute.TO_DATE);
        List<String> errors = validateUserInput(empl_surname, fromDate, toDate);
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        String uri = request.getRequestURI();
        String afterController = uri.substring(uri.indexOf("/controller/") + "/controller/".length());
        String firstSegment = afterController.contains("/")
                ? afterController.substring(0, afterController.indexOf("/"))
                : afterController;
        Map<String, String> urlParams;
        if (!errors.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, errors.get(0));
            if (firstSegment.equals("manager")){
                RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.MANAGER_PRODUCTS, urlParams);
            }
            else {
                RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.CASHIER_PRODUCTS, urlParams);
            }            return RedirectionManager.REDIRECTION;
        }

        List<Check> checks = checkService.searchChecksByEmployeeSurnamePerPeriod(empl_surname, LocalDate.parse(fromDate), LocalDate.parse(toDate));

        if (checks.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, Message.CHECK_IS_NOT_FOUND);
            if (firstSegment.equals("manager")){
                RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.MANAGER_CHECKS, urlParams);
            }
            else {
                RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.CASHIER_CHECKS, urlParams);
            }            return RedirectionManager.REDIRECTION;
        }

        request.setAttribute(Attribute.CHECK, checks);
        request.setAttribute(Attribute.CUSTOMER_CARDS, customerCardService.getAllCustomer_cards());
        request.setAttribute(Attribute.EMPLOYEES, employeeService.getAllEmployees());
        return Page.ALL_CHECKS_VIEW;
    }

    private List<String> validateUserInput(String empl_surname, String fromDate, String toDate) {
        List<String> errors = new ArrayList<>();

        AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();
        fieldValidator.validateField(FieldValidatorKey.SURNAME, empl_surname, errors);
        fieldValidator.validateField(FieldValidatorKey.DATE, fromDate, errors);
        fieldValidator.validateField(FieldValidatorKey.DATE, toDate, errors);
        return errors;
    }
}