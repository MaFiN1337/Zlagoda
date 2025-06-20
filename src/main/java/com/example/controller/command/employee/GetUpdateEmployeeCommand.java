package com.example.controller.command.employee;

import com.example.constants.Attribute;
import com.example.constants.Page;
import com.example.controller.command.Command;
import com.example.entity.Employee;
import com.example.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class GetUpdateEmployeeCommand implements Command {

    private final EmployeeService employeeService;

    public GetUpdateEmployeeCommand(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_employee = String.valueOf(request.getParameter(Attribute.ID_EMPLOYEE));

        Optional<Employee> employee = employeeService.getEmployeeById(id_employee);
        request.setAttribute(Attribute.EMPLOYEE, employee.get());

        return Page.ADD_UPDATE_EMPLOYEE_VIEW;
    }
}
