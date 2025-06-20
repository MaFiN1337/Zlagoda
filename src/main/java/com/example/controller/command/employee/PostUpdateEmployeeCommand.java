package com.example.controller.command.employee;

import com.example.constants.Attribute;
import com.example.constants.ServletPath;
import com.example.controller.command.Command;
import com.example.controller.utils.HttpWrapper;
import com.example.controller.utils.RedirectionManager;
import com.example.entity.Employee;
import com.example.entity.Role;
import com.example.locale.Message;
import com.example.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PostUpdateEmployeeCommand implements Command {

    private final EmployeeService employeeService;

    public PostUpdateEmployeeCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Employee employee = getUserInput(request);
//        List<String> errors = validateUserInput(employee);

//        if (errors.isEmpty()) {
            employeeService.updateEmployee(employee);
            redirectToAllEmployeesPageWithSuccessMessage(request, response);
            return RedirectionManager.REDIRECTION;
//        }

//        addRequestAttributes(request, employee, errors);
//        return Page.ADD_UPDATE_EMPLOYEE_VIEW;
    }

    private Employee getUserInput(HttpServletRequest request) {
        return new Employee.Builder().setId(request.getParameter(Attribute.ID_EMPLOYEE))
                .setName(request.getParameter(Attribute.EMPLOYEE_NAME))
                .setSurname(request.getParameter(Attribute.SURNAME)).setPatronymic(request.getParameter(Attribute.SURNAME)).setSalary(BigDecimal.valueOf(Long.parseLong(request.getParameter(Attribute.SALARY))))
                .setPhone(request.getParameter(Attribute.PHONE_NUMBER)).setDate_of_birth(LocalDate.parse(request.getParameter(Attribute.DATE_OF_BIRTH)))
                .setRole(Role.valueOf(request.getParameter(Attribute.ROLE))).setDate_of_start(LocalDate.parse(request.getParameter(Attribute.DATE_OF_START)))
                .setCity(request.getParameter(Attribute.CITY)).setStreet(request.getParameter(Attribute.STREET))
                .setZip_code(request.getParameter(Attribute.ZIP_CODE)).build();
    }

//    private List<String> validateUserInput(Employee employee) {
//        return EmployeeValidator.getInstance().validate(employee);
//    }

    private void redirectToAllEmployeesPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_EMPLOYEE_UPDATE);
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_EMPLOYEES, urlParams);
    }

//    private void addRequestAttributes(HttpServletRequest request, Employee employee, List<String> errors) {
//        request.setAttribute(Attribute.EMPLOYEE, employee);
//        request.setAttribute(Attribute.ERRORS, errors);
//    }
}