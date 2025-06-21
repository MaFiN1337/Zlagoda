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

    .form-inline {
        display: inline-block;
        margin-right: 10px;
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

<button class="btn" onclick="document.getElementById('searchCustomerCardSurname').style.display='block'">
    <fmt:message key="zlagoda.card.searchBySurname" bundle="${rb}" />
</button>

<button class="btn"
        onclick="location.href='${pageContext.request.contextPath}/controller/manager/customerCards/addCustomerCard';">
    <fmt:message key="zlagoda.add" bundle="${rb}" />
</button>
<button class="btn" onclick="document.getElementById('searchCardDiscountCategory').style.display='block'">
    <fmt:message key="zlagoda.card.discountForEachCategory" bundle="${rb}" />
</button>

<div id="searchCustomerCardSurname" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" onclick="document.getElementById('searchCustomerCardSurname').style.display='none'">&times;</span>
            <h4><fmt:message key="zlagoda.card.searchBySurname" bundle="${rb}" /></h4>
        </div>
        <div class="modal-body">
            <form action="${pageContext.request.contextPath}/controller/manager/customerCards/searchBySurname" method="POST">
                <label for="cust_surname">
                    <fmt:message key="zlagoda.customer.surname" bundle="${rb}" />
                </label><br>
                <input type="text" name="cust_surname" id="cust_surname" style="width: 100%; padding: 8px; margin-top: 5px;"><br><br>
                <div class="modal-footer">
                    <button class="btn" type="submit">
                        <fmt:message key="zlagoda.search" bundle="${rb}" />
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="searchCardDiscountCategory" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" onclick="document.getElementById('searchCardDiscountCategory').style.display='none'">&times;</span>
            <h4><fmt:message key="zlagoda.card.discountForEachCategory" bundle="${rb}" /></h4>
        </div>
        <div class="modal-body">
            <form action="${pageContext.request.contextPath}/controller/manager/customerCards/searchDiscountForEachCategory" method="POST">
                <label for="card_number">
                    <fmt:message key="zlagoda.card.number" bundle="${rb}" />
                </label><br>
                <input type="text" name="card_number" id="card_number" style="width: 100%; padding: 8px; margin-top: 5px;"><br><br>
                <div class="modal-footer">
                    <button class="btn" type="submit">
                        <fmt:message key="zlagoda.search" bundle="${rb}" />
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>



<div class="container-fluid">
        <div class="back-btn-container">
            <form action="${pageContext.request.contextPath}/controller/manager" method="get">
                <button type="submit" class="back-btn">Back to manager menu</button>
            </form>
        </div>
    </div>

<h2><fmt:message key="zlagoda.customer_cards" bundle="${rb}" /></h2>

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
        <th><fmt:message key="zlagoda.surname" bundle="${rb}" /></th>
        <th><fmt:message key="zlagoda.patronymic" bundle="${rb}" /></th>
        <th><fmt:message key="zlagoda.phone" bundle="${rb}" /></th>
        <th><fmt:message key="zlagoda.city" bundle="${rb}" /></th>
        <th><fmt:message key="zlagoda.street" bundle="${rb}" /></th>
        <th><fmt:message key="zlagoda.zip_code" bundle="${rb}" /></th>
        <th><fmt:message key="zlagoda.percent" bundle="${rb}" /></th>
        <th>Methods</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${customer_cards}" var="customerCard" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${customerCard.getName()}</td>
            <td>${customerCard.getSurname()}</td>
            <td>${customerCard.getPatronymic()}</td>
            <td>${customerCard.getPhoneNumber()}</td>
            <td>${customerCard.getCity()}</td>
            <td>${customerCard.getStreet()}</td>
            <td>${customerCard.getZip_code()}</td>
            <td>${customerCard.getPercent()}</td>
            <td>
                <a href="./customerCards/updateCustomerCard?card_number=${customerCard.getNumber()}">
                    <fmt:message key="zlagoda.update" bundle="${rb}" />
                </a>
                <br>
                <a href="./customerCards/deleteCustomerCard?card_number=${customerCard.getNumber()}">
                    <fmt:message key="zlagoda.delete" bundle="${rb}" />
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@include file="footer.jsp"%>