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

    .btn-group {
        margin: 20px;
        text-align: left;
    }

    .btn {
        padding: 8px 16px;
        margin-right: 10px;
        background-color: #2c3e50;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    .btn:hover {
        background-color: #34495e;
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

    /* Modal */
    .modal {
        display: none;
        position: fixed;
        z-index: 1000;
        padding-top: 60px;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0,0,0,0.4);
    }

    .modal-content {
        background-color: #fff;
        margin: auto;
        padding: 20px;
        border: 1px solid #888;
        width: 40%;
        border-radius: 6px;
    }

    .modal-header, .modal-footer {
        text-align: right;
    }

    .modal-header h4 {
        margin: 0;
    }

    .close {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
        cursor: pointer;
    }

    .close:hover {
        color: black;
    }

</style>

<div style="text-align: left; margin-bottom: 15px;">
    <form action="${pageContext.request.contextPath}/controller/manager" method="get">
        <button type="submit"
                style="background-color: #ff9800; color: white; padding: 10px 20px; border: none;
                       border-radius: 5px; cursor: pointer; font-weight: bold;">
            Back to manager menu
        </button>
    </form>
</div>



<h2><fmt:message key="zlagoda.products" bundle="${rb}" /></h2>

<c:if test="${not empty param.success}">
    <div class="alert alert-success">
        <fmt:message key="${param.success}" bundle="${rb}" />
    </div>
</c:if>

<c:if test="${not empty param.error}">
    <div class="alert alert-danger">
        <fmt:message key="${param.error}" bundle="${rb}" />
    </div>
</c:if>

<table>
    <thead>
    <tr>
        <th>#</th>
        <th><fmt:message key="zlagoda.name" bundle="${rb}" /></th>
        <th><fmt:message key="zlagoda.characteristics" bundle="${rb}" /></th>
        <th><fmt:message key="zlagoda.category" bundle="${rb}" /></th>
        <th>Methods</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${products}" var="product" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${product.getName()}</td>
            <td>${product.getCharacteristics()}</td>
            <td>${product.getCategory().getName()}</td>

            <td>
                <a href="./products/updateProduct?id_product=${product.getId()}">
                    <fmt:message key="zlagoda.update" bundle="${rb}" />
                </a>
                <br>
                <a href="./products/deleteProduct?id_product=${product.getId()}">
                    <fmt:message key="zlagoda.delete" bundle="${rb}" />
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@include file="footer.jsp"%>
