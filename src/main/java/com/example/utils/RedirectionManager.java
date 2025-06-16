package com.example.utils;

import com.example.exception.ServerException;
import com.example.locale.MessageUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class RedirectionManager {
    public static String REDIRECTION = "REDIRECTION";
    private static final String MESSAGE_ENCODING = "UTF-8";

    RedirectionManager() {
    }

    private static final class Holder {
        static final RedirectionManager INSTANCE = new RedirectionManager();
    }

    public static RedirectionManager getInstance() {
        return Holder.INSTANCE;
    }


    public void redirectWithParams(HttpWrapper httpWrapper, String redirectionPath, Map<String, String> urlParameters)
            throws IOException {
        String urlPathWithParams = generateUrlPath(httpWrapper.getRequest(), redirectionPath)
                + generateUrlParams(urlParameters);
        try {
            httpWrapper.getResponse().sendRedirect(urlPathWithParams);
        } catch (IOException e) {
            throw new ServerException(e);
        }
    }

    public void redirect(HttpWrapper httpWrapper, String path) {
        try {
            httpWrapper.getResponse().sendRedirect(generateUrlPath(httpWrapper.getRequest(), path));
        } catch (IOException e) {
            throw new ServerException(e);
        }
    }

    private String generateUrlPath(HttpServletRequest request, String path) {
        return new StringBuffer(request.getContextPath()).append(request.getServletPath()).append(path).toString();
    }

    public String generateUrlParams(Map<String, String> urlParameters) throws UnsupportedEncodingException {
        StringBuffer stringBuffer = new StringBuffer(MessageUtils.INTERROGATION_MARK);
        for (String urlParamName : urlParameters.keySet()) {
            stringBuffer.append(urlParamName).append(MessageUtils.EQUALITY_SIGN)
                    .append(URLEncoder.encode(urlParameters.get(urlParamName), MESSAGE_ENCODING))
                    .append(MessageUtils.AMPERSAND);
        }
        deleteLastAmpersand(stringBuffer);
        return stringBuffer.toString();
    }

    private void deleteLastAmpersand(StringBuffer stringBuffer) { stringBuffer.deleteCharAt(stringBuffer.length() - 1); }
}
