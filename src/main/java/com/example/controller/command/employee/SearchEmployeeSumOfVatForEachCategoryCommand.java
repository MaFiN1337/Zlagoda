package com.example.controller.command.employee;

import com.example.constants.Attribute;
import com.example.constants.Page;
import com.example.constants.ServletPath;
import com.example.controller.command.Command;
import com.example.controller.utils.HttpWrapper;
import com.example.controller.utils.RedirectionManager;
import com.example.dto.EmployeeTaxSummaryDto;
import com.example.entity.Employee;
import com.example.locale.Message;
import com.example.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchEmployeeSumOfVatForEachCategoryCommand implements Command {

    private final EmployeeService employeeService;

    public SearchEmployeeSumOfVatForEachCategoryCommand (EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ID_EMPLOYEE = request.getParameter(Attribute.ID_EMPLOYEE);
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams;


        List<EmployeeTaxSummaryDto> summaryDtos = employeeService
                .searchEmployeeSumOfVatForEachCategory(new Employee.Builder().setId(ID_EMPLOYEE).build());

        if (summaryDtos.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, Message.EMPLOYEE_IS_NOT_FOUND);
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.MANAGER_EMPLOYEES, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        request.setAttribute(Attribute.SUMMARY_DTOS, summaryDtos);
        return Page.SUM_VIEW;
    }
}
