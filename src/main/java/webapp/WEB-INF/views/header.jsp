<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="en_US" scope="session" />
<fmt:setBundle basename="/i18n/messages" var="rb" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <title>Zlagoda</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/styles.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap-3.3.7/css/bootstrap.min.css" />" />
</head>
<body>
<div class="container main-container">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/controller/">
                    <fmt:message key="zlagoda.main" bundle="${rb}" />
                </a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/controller/cashier">
                    <fmt:message key="zlagoda.employee.role.cashier" bundle="${rb}" />
                </a></li>
                <li><a href="${pageContext.request.contextPath}/controller/manager">
                    <fmt:message key="zlagoda.employee.role.manager" bundle="${rb}" />
                </a></li>
            </ul>
        </div>
    </nav>
</div>