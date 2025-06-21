package com.example.controller.command.employee;

import com.example.constants.Attribute;
import com.example.constants.Page;
import com.example.constants.ServletPath;
import com.example.controller.command.Command;
import com.example.controller.utils.HttpWrapper;
import com.example.controller.utils.RedirectionManager;
import com.example.entity.Employee;
import com.example.locale.Message;
import com.example.service.EmployeeService;
import com.example.validator.field.AbstractFieldValidatorHandler;
import com.example.validator.field.FieldValidatorKey;
import com.example.validator.field.FieldValidatorsChainGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class SearchEmployeesBySurnameCommand implements Command {

    private final EmployeeService employeeService;

    public SearchEmployeesBySurnameCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String surname = request.getParameter(Attribute.EMPLOYEE_SURNAME);
        List<String> errors = validateUserInput(surname);
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams;

        if (!errors.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, errors.get(0));
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.MANAGER_EMPLOYEES, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        List<Employee> employees = employeeService.searchEmployeesBySurname(surname);

        if (employees.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, Message.EMPLOYEE_IS_NOT_FOUND);
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.MANAGER_EMPLOYEES, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        request.setAttribute(Attribute.EMPLOYEES, employees);
        return Page.ALL_EMPLOYEES_VIEW;
    }

    private List<String> validateUserInput(String surname) {
        List<String> errors = new ArrayList<>();

        AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();
        fieldValidator.validateField(FieldValidatorKey.SURNAME, surname, errors);
        return errors;
    }
}
