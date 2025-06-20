<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="udt" uri="customtags"%>

<fmt:setLocale value="en_US" scope="session" />
<fmt:setBundle basename="i18n/messsages" var="rb" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <title>Error Page</title>
    <style type="">
        body {
            margin: 0;
            padding: 0;
            background-color: #f8d7da;
            font-family: Arial, sans-serif;
        }

        .error-container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #721c24;
        }

        .error-code {
            font-size: 96px;
            font-weight: bold;
        }

        .error-message {
            font-size: 24px;
            margin-bottom: 30px;
        }

        a {
            font-size: 18px;
            text-decoration: none;
            color: #721c24;
            padding: 8px 16px;
            border: 2px solid #721c24;
            border-radius: 5px;
            transition: background-color 0.3s, color 0.3s;
        }

        a:hover {
            background-color: #721c24;
            color: white;
        }
    </style>
</head>
<body>

<div class="error-container">
    <div class="error-code">
        ${pageContext.errorData.statusCode}
    </div>
    <div class="error-message">
        <fmt:message key="zlagoda.error.serverError" bundle="${rb}" />
    </div>
    <a href="${pageContext.request.contextPath}/">
        Back to home page
    </a>
</div>

</body>
</html>
