<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="udt" uri="customtags"%>


<fmt:setLocale value="en_US" scope="session" />
<fmt:setBundle basename="i18n/messages" var="rb" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <title>Error Page</title>
    <style type="">
        body {
            margin: 0;
            padding: 0;
            background-color: #eef2f5;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .error-container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #2c3e50;
        }

        .error-code {
            font-size: 90px;
            font-weight: bold;
            color: #e74c3c;
        }

        .error-message {
            font-size: 24px;
            margin-bottom: 30px;
        }

        a {
            font-size: 18px;
            text-decoration: none;
            color: #2c3e50;
            border: 2px solid #2c3e50;
            padding: 10px 20px;
            border-radius: 6px;
            transition: all 0.3s ease;
        }

        a:hover {
            background-color: #2c3e50;
            color: #ffffff;
        }
    </style>
</head>
<body>

<div class="error-container">
    <div class="error-code">
        ${pageContext.errorData.statusCode}
    </div>
    <div class="error-message">
        <fmt:message key="zlagoda.error.pageNotFoundError" bundle="${rb}" />
    </div>
    <a href="${pageContext.request.contextPath}/controller/">
        Back to home page
    </a>
</div>

</body>
</html>