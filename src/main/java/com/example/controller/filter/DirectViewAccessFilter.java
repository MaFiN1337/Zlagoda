package com.example.controller.filter;

import com.example.constants.Attribute;
import com.example.constants.ServletPath;
import com.example.controller.utils.RedirectionManager;
import com.example.locale.Message;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = { "/views/*" })
public class DirectViewAccessFilter implements Filter{

    private final static Logger LOGGER = Logger.getLogger(DirectViewAccessFilter.class);
    private static final String UNAUTHORIZED_ACCESS = "Unauthorized access to the resource: ";

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        logInfoAboutUnauthorizedAccess(httpRequest.getRequestURI());
        httpResponse.sendRedirect(toHomePageWithErrorMessage(httpRequest.getContextPath()));
    }

    public void destroy() {
    }

    private String toHomePageWithErrorMessage(String contextPath) throws UnsupportedEncodingException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.ERROR, Message.DIRECT_VIEW_ACCESS_ERROR);
        return new StringBuffer(contextPath).append(ServletPath.HOME)
                .append(RedirectionManager.getInstance().generateUrlParams(urlParams)).toString();
    }

    private void logInfoAboutUnauthorizedAccess(String uri) {
        LOGGER.info(UNAUTHORIZED_ACCESS + uri);
    }
}
