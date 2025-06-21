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

<style>

    .btn {
        padding: 10px 18px;
        background-color: #2c3e50;
        color: white;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        font-size: 15px;
        font-weight: 500;
        transition: background-color 0.3s ease, transform 0.2s ease;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
    }

    .btn:hover {
        background-color: #1a252f;
        transform: translateY(-1px);
    }

    .btn:active {
        background-color: #34495e;
        transform: translateY(0);
    }

    .btn:focus {
        outline: none;
        box-shadow: 0 0 0 3px rgba(44, 62, 80, 0.4);
    }
</style>

<button class="btn" onclick="document.getElementById('searchAmountPerPeriod').style.display='block'">
    <fmt:message key="zlagoda.store.amountPerPeriod" bundle="${rb}" />
</button>
<div id="searchAmountPerPeriod" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" onclick="document.getElementById('searchAmountPerPeriod').style.display='none'">&times;</span>
            <h4><fmt:message key="zlagoda.store.amountPerPeriod" bundle="${rb}" /></h4>
        </div>
        <div class="modal-body">
            <form action="${pageContext.request.contextPath}/controller/manager/storeProducts/searchAmountPerPeriod" method="POST">
                <label><fmt:message key="zlagoda.date.start" bundle="${rb}" /></label><br>
                <input type="date" name="start_date" required><br><br>
                <label><fmt:message key="zlagoda.date.end" bundle="${rb}" /></label><br>
                <input type="date" name="end_date" required><br><br>
                <div class="modal-footer">
                    <button class="btn" type="submit">
                        <fmt:message key="zlagoda.search" bundle="${rb}" />
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<form action="${pageContext.request.contextPath}/controller/cashier/storeProducts/searchNonPromoByName" method="POST">
    <button class="btn" type="submit">
        <fmt:message key="zlagoda.store.nonPromoSortedByName" bundle="${rb}" />
    </button>
</form>

<form action="${pageContext.request.contextPath}/controller/cashier/storeProducts/searchNonPromoByProductNum" method="POST">
    <button class="btn" type="submit">
        <fmt:message key="zlagoda.store.nonPromoSortedByProductNum" bundle="${rb}" />
    </button>
</form>

<form action="${pageContext.request.contextPath}/controller/cashier/storeProducts/searchPromoByName" method="POST">
    <button class="btn" type="submit">
        <fmt:message key="zlagoda.store.promoSortedByName" bundle="${rb}" />
    </button>
</form>

<form action="${pageContext.request.contextPath}/controller/cashier/storeProducts/searchPromoByProductNum" method="POST">
    <button class="btn" type="submit">
        <fmt:message key="zlagoda.store.promoSortedByProductNum" bundle="${rb}" />
    </button>
</form>

<button class="btn" onclick="document.getElementById('searchStoreProductByUpc').style.display='block'">
    <fmt:message key="zlagoda.store.detailsByUpc" bundle="${rb}" />
</button>
<div id="searchStoreProductByUpc" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" onclick="document.getElementById('searchStoreProductByUpc').style.display='none'">&times;</span>
            <h4><fmt:message key="zlagoda.store.detailsByUpc" bundle="${rb}" /></h4>
        </div>
        <div class="modal-body">
            <form action="${pageContext.request.contextPath}/controller/cashier/storeProducts/searchDetailsByUpc" method="POST">
                <label for="UPC"><fmt:message key="zlagoda.store.upc" bundle="${rb}" /></label><br>
                <input type="text" name="UPC" id="UPC" required style="width: 100%; padding: 8px; margin-top: 5px;"><br><br>
                <div class="modal-footer">
                    <button class="btn" type="submit">
                        <fmt:message key="zlagoda.search" bundle="${rb}" />
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<form action="${pageContext.request.contextPath}/controller/cashier/storeProducts/searchSortedByName" method="POST">
    <button class="btn" type="submit">
        <fmt:message key="zlagoda.store.sortedByName" bundle="${rb}" />
    </button>
</form>

<form action="${pageContext.request.contextPath}/controller/cashier/storeProducts/searchSortedByProductsNum" method="POST">
    <button class="btn" type="submit">
        <fmt:message key="zlagoda.store.sortedByProductNum" bundle="${rb}" />
    </button>
</form>


<div style="text-align: left; margin-bottom: 15px;">
    <form action="${pageContext.request.contextPath}/controller/cashier" method="get">
        <button type="submit"
                style="background-color: #ff9800; color: white; padding: 10px 20px; border: none;
                       border-radius: 5px; cursor: pointer; font-weight: bold;">
            Back to cashier menu
        </button>
    </form>
</div>



<h2><fmt:message key="zlagoda.storeProducts" bundle="${rb}" /></h2>

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
        <th><fmt:message key="zlagoda.selling_price" bundle="${rb}" /></th>
        <th><fmt:message key="zlagoda.products_number" bundle="${rb}" /></th>
        <th><fmt:message key="zlagoda.promotional_product" bundle="${rb}" /></th>
        <th><fmt:message key="zlagoda.product" bundle="${rb}" /></th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${store_products}" var="store_product" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${store_product.getSelling_price()}</td>
            <td>${store_product.getProducts_number()}</td>
            <td>${store_product.getPromotional_product()}</td>
            <td>${store_product.getProduct().getName()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@include file="footer.jsp"%>

