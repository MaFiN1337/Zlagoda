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

public class SearchChecksPerPeriodCommand implements Command {

    private final CheckService checkService;
    private final Customer_cardService customerCardService;
    private final EmployeeService employeeService;

    public SearchChecksPerPeriodCommand(CheckService checkService, Customer_cardService customerCardService, EmployeeService employeeService) {
        this.checkService = checkService;
        this.customerCardService = customerCardService;
        this.employeeService = employeeService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fromDate = request.getParameter(Attribute.FROM_DATE);
        String toDate = request.getParameter(Attribute.TO_DATE);
        List<String> errors = validateUserInput(fromDate, toDate);
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams;
        if (!errors.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, errors.get(0));
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.MANAGER_CHECKS, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        List<Check> checks = checkService.searchChecksPerPeriod(LocalDate.parse(fromDate), LocalDate.parse(toDate));

        if (checks.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, Message.CHECK_IS_NOT_FOUND);
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.MANAGER_CHECKS, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        request.setAttribute(Attribute.CHECK, checks);
        request.setAttribute(Attribute.CUSTOMER_CARDS, customerCardService.getAllCustomer_cards());
        request.setAttribute(Attribute.EMPLOYEES, employeeService.getAllEmployees());
        return Page.ALL_CHECKS_VIEW;
    }

    private List<String> validateUserInput(String fromDate, String toDate) {
        List<String> errors = new ArrayList<>();

        AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();
        fieldValidator.validateField(FieldValidatorKey.DATE, fromDate, errors);
        fieldValidator.validateField(FieldValidatorKey.DATE, toDate, errors);
        return errors;
    }
}
