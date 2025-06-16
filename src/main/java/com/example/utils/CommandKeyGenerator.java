package com.example.utils;

import javax.servlet.http.HttpServletRequest;

public final class CommandKeyGenerator {

    private static final String CONTROLLER_PATH = ".*/controller/";
    private static final String REPLACEMENT = "";
    private static final String DELIMITER = ":";

    private CommandKeyGenerator() {
    }

    public static String generateCommandKeyFromRequest(HttpServletRequest request) {
        String method = request.getMethod().toUpperCase();
        String path = request.getRequestURI().replaceAll(CONTROLLER_PATH, REPLACEMENT);
        return method + DELIMITER + path;          // returns key
    }
}

