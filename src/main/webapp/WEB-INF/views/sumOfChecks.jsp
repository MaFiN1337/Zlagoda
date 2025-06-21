<%@include file="header.jsp"%>

<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f0f4f8;
        color: #333;
        margin: 0;
        padding: 0;
    }

    h2 {
        text-align: center;
        margin: 30px 0;
        color: #2c3e50;
    }

    .alert {
        width: 50%;
        margin: 10px auto;
        padding: 15px;
        border-radius: 5px;
    }

    .alert-success {
        background-color: #dff0d8;
        color: #3c763d;
    }

    .alert-danger {
        background-color: #f2dede;
        color: #a94442;
    }

    table {
        width: 80%;
        margin: 0 auto 50px auto;
        border-collapse: collapse;
        background-color: white;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    th, td {
        padding: 12px 15px;
        border: 1px solid #ccc;
        text-align: center;
    }

    th {
        background-color: #2c3e50;
        color: white;
    }

    td a {
        color: #2980b9;
        text-decoration: none;
    }

    td a:hover {
        text-decoration: underline;
    }


    .modal-header h4 {
        margin: 0;
    }


    .form-inline input[type="text"] {
        padding: 6px;
        margin-right: 5px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    .back-btn-container {
        text-align: left;
        margin: 20px;
    }

    .back-btn-container form {
        display: inline-block;
    }

    .back-btn {
        background-color: #ff9800;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-weight: bold;
    }

    .back-btn:hover {
        background-color: #e68a00;
    }

</style>

<div class="container-fluid">
    <div class="back-btn-container">
        <form action="${pageContext.request.contextPath}/controller/manager/employees" method="get">
            <button type="submit" class="back-btn">Back to manager employee screen</button>
        </form>
    </div>
</div>

<table>
    <thead>
    <tr>
        <th>#</th>
        <th><fmt:message key="zlagoda.category" bundle="${rb}" /></th>
        <th><fmt:message key="zlagoda.taxAmount" bundle="${rb}" /></th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${summary_dtos}" var="summary_dto" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${summary_dto.getCategoryName()}</td>
            <td>${summary_dto.getTaxAmount()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@include file="footer.jsp"%>