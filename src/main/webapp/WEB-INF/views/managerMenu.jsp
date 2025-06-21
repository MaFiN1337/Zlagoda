<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="/WEB-INF/views/header.jsp"%>

<style>
    .manager-menu {
        margin-top: 50px;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .menu-buttons {
        display: grid;
        grid-template-columns: repeat(2, 200px);
        gap: 20px;
        justify-content: center;
        margin-top: 30px;
    }

    .menu-buttons a {
        display: inline-block;
        padding: 15px 25px;
        text-align: center;
        font-size: 16px;
        background-color: #005580;
        color: white;
        text-decoration: none;
        border-radius: 10px;
        transition: background-color 0.3s ease;
    }

    .menu-buttons a:hover {
        background-color: #0077b3;
    }

    .menu-title {
        font-size: 28px;
        font-weight: bold;
        color: #00264d;
    }
</style>

<div class="container-fluid manager-menu">
    <div class="menu-title">
        <fmt:message key="zlagoda.manager.menu" bundle="${rb}" />
    </div>

    <div class="menu-buttons">
        <a href="${pageContext.request.contextPath}/controller/manager/employees">Employee</a>
        <a href="${pageContext.request.contextPath}/controller/manager/checks">Check</a>
        <a href="${pageContext.request.contextPath}/controller/manager/storeProducts">Store Product</a>
        <a href="${pageContext.request.contextPath}/controller/manager/products">Product</a>
        <a href="${pageContext.request.contextPath}/controller/manager/categories">Category</a>
        <a href="${pageContext.request.contextPath}/controller/manager/customerCards">Customer Card</a>
    </div>
</div>

<%@include file="/WEB-INF/views/footer.jsp"%>

