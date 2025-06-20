<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="udt" uri="customtags"%>

<fmt:setLocale value="en_US" scope="session" />
<fmt:setBundle basename="i18n/messsages" var="rb" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <title>Zlagoda</title>
    <style>
        body {
            margin: 0;
            background-color: #f0f2f5;
            font-family: Arial, sans-serif;
        }

        .header {
            background-color: #2c3e50;
            color: #ecf0f1;
            width: 220px;
            padding: 20px 10px;
        }

        .brand {
            text-align: center;
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .brand a {
            color: #ecf0f1;
            text-decoration: none;
        }

        .brand a:hover {
            text-decoration: underline;
        }

        .nav-links {
            padding: 0;
            margin: 0;
        }

        .nav-links li {
            list-style: none;
            margin: 10px 0;
            text-align: center;
        }

        .nav-links a {
            color: #bdc3c7;
            text-decoration: none;
            font-size: 14px;
        }

        .nav-links a:hover {
            color: #ffffff;
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="header">
    <div class="brand">
        <a href="${pageContext.request.contextPath}/controller/">
            <fmt:message key="zlagoda.main" bundle="${rb}" />
        </a>
    </div>
    <ul class="nav-links">
        <li>
            <a href="${pageContext.request.contextPath}/controller/products">
                <fmt:message key="zlagoda.employee.role.cashier" bundle="${rb}" />
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/controller/products">
                <fmt:message key="zlagoda.employee.role.manager" bundle="${rb}" />
            </a>
        </li>
    </ul>
</div>
</body>
</html>