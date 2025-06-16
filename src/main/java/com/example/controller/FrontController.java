package com.example.controller;

import com.example.constants.Attribute;
import com.example.constants.ServletPath;
import com.example.controller.command.Command;
import com.example.exception.ServiceException;
import com.example.controller.utils.CommandKeyGenerator;
import com.example.controller.utils.HttpWrapper;
import com.example.controller.utils.RedirectionManager;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = { "/controller/*" }, loadOnStartup = 1)
public class FrontController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(FrontController.class);
    private static final long serialVersionUID = 1L;

    public FrontController() { }

    @Override
    public void init() throws ServletException { super.init(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpWrapper httpWrapper = new HttpWrapper(request, response);
        String commandKey = CommandKeyGenerator.generateCommandKeyFromRequest(request);
        Command command = CommandFactory.getCommand(commandKey);
        try {
            String commandResultedResource = command.execute(request, response);
            forwardToCommandResultedPage(httpWrapper, commandResultedResource);
        } catch (ServiceException ex) {
            LOGGER.error("Error has occurred while command execution with key: " + commandKey);
            redirectToHomePageWithErrorMessage(httpWrapper, ex);
        }
    }

    private void forwardToCommandResultedPage(HttpWrapper httpWrapper, String resultedRedirectResource)
            throws ServletException, IOException {
        if (!resultedRedirectResource.contains(RedirectionManager.REDIRECTION)) {
            httpWrapper.getRequest().getRequestDispatcher(resultedRedirectResource).forward(httpWrapper.getRequest(),
                    httpWrapper.getResponse());
        }
    }

    private void redirectToHomePageWithErrorMessage(HttpWrapper httpWrapper, ServiceException ex) throws IOException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.ERROR, ex.getMessage());
        RedirectionManager.getInstance().redirectWithParams(httpWrapper, ServletPath.HOME, urlParams);
    }
}

