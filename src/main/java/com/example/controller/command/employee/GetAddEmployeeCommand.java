package com.example.controller.command.employee;

import com.example.constants.Page;
import com.example.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetAddEmployeeCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        return Page.ADD_UPDATE_EMPLOYEE_VIEW;
    }
}
