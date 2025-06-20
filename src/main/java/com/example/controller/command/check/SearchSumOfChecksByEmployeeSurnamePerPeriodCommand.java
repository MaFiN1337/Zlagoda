package com.example.controller.command.check;

import com.example.constants.Attribute;
import com.example.constants.Page;
import com.example.constants.ServletPath;
import com.example.controller.command.Command;
import com.example.controller.utils.HttpWrapper;
import com.example.controller.utils.RedirectionManager;
import com.example.dto.CheckSumDto;
import com.example.locale.Message;
import com.example.service.CheckService;
import com.example.validator.field.AbstractFieldValidatorHandler;
import com.example.validator.field.FieldValidatorKey;
import com.example.validator.field.FieldValidatorsChainGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class SearchSumOfChecksByEmployeeSurnamePerPeriodCommand implements Command {

    private final CheckService checkService;

    public SearchSumOfChecksByEmployeeSurnamePerPeriodCommand(CheckService checkService) {
        this.checkService = checkService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String emplSurname = request.getParameter(Attribute.EMPLOYEE_SURNAME);
        String fromDate = request.getParameter(Attribute.FROM_DATE);
        String toDate = request.getParameter(Attribute.TO_DATE);
        List<String> errors = validateUserInput(emplSurname, fromDate, toDate);
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        Map<String, String> urlParams;
        if (!errors.isEmpty()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, errors.get(0));
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.MANAGER_CHECKS, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        Optional<CheckSumDto> sum = checkService.searchSumOfChecksByEmployeeSurnamePerPeriod(emplSurname, LocalDate.parse(fromDate), LocalDate.parse(toDate));

        if (!sum.isPresent()) {
            urlParams = new HashMap<>();
            urlParams.put(Attribute.ERROR, Message.CHECK_IS_NOT_FOUND);
            RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.MANAGER_CHECKS, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        request.setAttribute(Attribute.SUM, sum);
        return Page.SUM_VIEW;
    }

    private List<String> validateUserInput(String surname, String fromDate, String toDate) {
        List<String> errors = new ArrayList<>();

        AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();
        fieldValidator.validateField(FieldValidatorKey.SURNAME, surname, errors);
        fieldValidator.validateField(FieldValidatorKey.DATE, fromDate, errors);
        fieldValidator.validateField(FieldValidatorKey.DATE, toDate, errors);
        return errors;
    }
}