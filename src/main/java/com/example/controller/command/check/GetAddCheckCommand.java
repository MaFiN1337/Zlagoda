package com.example.controller.command.check;

import com.example.constants.Attribute;
import com.example.constants.Page;
import com.example.controller.command.Command;
import com.example.service.CheckService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetAddCheckCommand implements Command {

    private final CheckService checkService;

    public GetAddCheckCommand(CheckService checkService) {
        this.checkService = checkService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(Attribute.CHECKS, checkService.getAllChecks());
        return Page.ADD_UPDATE_CHECK_VIEW;
    }
}