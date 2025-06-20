package com.example.controller.command.check;

import com.example.constants.Attribute;
import com.example.constants.ServletPath;
import com.example.controller.command.Command;
import com.example.controller.utils.HttpWrapper;
import com.example.controller.utils.RedirectionManager;
import com.example.entity.Check;
import com.example.entity.Customer_card;
import com.example.entity.Employee;
import com.example.locale.Message;
import com.example.service.CheckService;
import com.example.service.Customer_cardService;
import com.example.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class PostAddCheckCommand implements Command {

    private final CheckService checkService;
    private final Customer_cardService customerCardService;
    private final EmployeeService employeeService;

    public PostAddCheckCommand(CheckService checkService, Customer_cardService customerCardService, EmployeeService employeeService) {
        this.checkService = checkService;
        this.customerCardService = customerCardService;
        this.employeeService = employeeService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Check check = getUserInput(request);
        checkService.createCheck(check);
        redirectToAllChecksPageWithSuccessMessage(request, response);
        return RedirectionManager.REDIRECTION;
    }

    private Check getUserInput(HttpServletRequest request) {
        return new Check.Builder().setNumber(request.getParameter(Attribute.CHECK_NUMBER)).setPrint_date(LocalDateTime.parse(request.getParameter(Attribute.PRINT_DATE)))
                .setSum_total(BigDecimal.valueOf(Long.parseLong(request.getParameter(Attribute.CHECK_NUMBER)))).setVat(BigDecimal.valueOf(Long.parseLong(request.getParameter(Attribute.CHECK_NUMBER))))
                .setCustomer_card(
                         new Customer_card.Builder().setNumber(request.getParameter(Attribute.CARD_NUMBER))
                        .build()
                )
                .setEmployee(
                        new Employee.Builder().setId(request.getParameter(Attribute.ID_EMPLOYEE)).build()
                ).build();
    }


    private void redirectToAllChecksPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, Message.SUCCESS_CHECK_ADDITION);
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.ALL_CHECKS, urlParams);
    }
}