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

<div class="btn-group">

    <button class="btn"
            onclick="location.href='${pageContext.request.contextPath}/controller/manager/checks/addCheck';">
        <fmt:message key="zlagoda.add" bundle="${rb}" />
    </button>

    <button class="btn" onclick="document.getElementById('modalByEmpId').style.display='block'">
        <fmt:message key="zlagoda.check.searchByEmployeeId" bundle="${rb}" />
    </button>

    <button class="btn" onclick="document.getElementById('modalByCheckNumber').style.display='block'">
        <fmt:message key="zlagoda.check.searchByNumber" bundle="${rb}" />
    </button>

    <button class="btn" onclick="document.getElementById('modalByEmpIdPeriod').style.display='block'">
        <fmt:message key="zlagoda.check.searchByEmployeeIdPeriod" bundle="${rb}" />
    </button>

    <button class="btn" onclick="document.getElementById('modalByEmpSurname').style.display='block'">
        <fmt:message key="zlagoda.check.searchByEmployeeSurname" bundle="${rb}" />
    </button>

    <button class="btn" onclick="document.getElementById('modalByEmpSurnamePeriod').style.display='block'">
        <fmt:message key="zlagoda.check.searchByEmployeeSurnamePeriod" bundle="${rb}" />
    </button>

    <button class="btn" onclick="document.getElementById('modalChecksPerPeriod').style.display='block'">
        <fmt:message key="zlagoda.check.searchPerPeriod" bundle="${rb}" />
    </button>

    <button class="btn" onclick="document.getElementById('modalSumBySurnamePeriod').style.display='block'">
        <fmt:message key="zlagoda.check.searchSumBySurnamePeriod" bundle="${rb}" />
    </button>

    <button class="btn" onclick="document.getElementById('modalSumPerPeriod').style.display='block'">
        <fmt:message key="zlagoda.check.searchSumPerPeriod" bundle="${rb}" />
    </button>

</div>


<div id="modalByEmpId" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" onclick="document.getElementById('modalByEmpId').style.display='none'">&times;</span>
            <h4><fmt:message key="zlagoda.check.searchByEmployeeId" bundle="${rb}"/></h4>
        </div>
        <div class="modal-body">
            <form action="${pageContext.request.contextPath}/controller/manager/checks/searchByEmployeeId" method="POST">
                <label for="employeeId"><fmt:message key="zlagoda.employee.id" bundle="${rb}"/></label><br>
                <input type="text" name="employeeId" id="employeeId" style="width: 100%; padding: 8px;"><br><br>
                <div class="modal-footer">
                    <button class="btn" type="submit"><fmt:message key="zlagoda.search" bundle="${rb}"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="modalByCheckNumber" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" onclick="document.getElementById('modalByCheckNumber').style.display='none'">&times;</span>
            <h4><fmt:message key="zlagoda.check.searchByNumber" bundle="${rb}"/></h4>
        </div>
        <div class="modal-body">
            <form action="${pageContext.request.contextPath}/controller/manager/checks/searchByNumber" method="POST">
                <label for="checkNumber"><fmt:message key="zlagoda.check.number" bundle="${rb}"/></label><br>
                <input type="text" name="checkNumber" id="checkNumber" style="width: 100%; padding: 8px;"><br><br>
                <div class="modal-footer">
                    <button class="btn" type="submit"><fmt:message key="zlagoda.search" bundle="${rb}"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="modalByEmpIdPeriod" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" onclick="document.getElementById('modalByEmpIdPeriod').style.display='none'">&times;</span>
            <h4><fmt:message key="zlagoda.check.searchByEmployeeIdPeriod" bundle="${rb}"/></h4>
        </div>
        <div class="modal-body">
            <form action="${pageContext.request.contextPath}/controller/manager/checks/searchByEmployeeIdPerPeriod" method="POST">
                <label><fmt:message key="zlagoda.employee.id" bundle="${rb}"/></label><br>
                <input type="text" name="employeeId" style="width: 100%; padding: 8px;"><br><br>
                <label><fmt:message key="zlagoda.date.from" bundle="${rb}"/></label><br>
                <input type="date" name="fromDate" style="width: 100%; padding: 8px;"><br><br>
                <label><fmt:message key="zlagoda.date.to" bundle="${rb}"/></label><br>
                <input type="date" name="toDate" style="width: 100%; padding: 8px;"><br><br>
                <div class="modal-footer">
                    <button class="btn" type="submit"><fmt:message key="zlagoda.search" bundle="${rb}"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="modalByEmpSurname" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" onclick="document.getElementById('modalByEmpSurname').style.display='none'">&times;</span>
            <h4><fmt:message key="zlagoda.check.searchByEmployeeSurname" bundle="${rb}"/></h4>
        </div>
        <div class="modal-body">
            <form action="${pageContext.request.contextPath}/controller/manager/checks/searchByEmployeeSurname" method="POST">
                <label><fmt:message key="zlagoda.surname" bundle="${rb}"/></label><br>
                <input type="text" name="surname" style="width: 100%; padding: 8px;"><br><br>
                <div class="modal-footer">
                    <button class="btn" type="submit"><fmt:message key="zlagoda.search" bundle="${rb}"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="modalByEmpSurnamePeriod" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" onclick="document.getElementById('modalByEmpSurnamePeriod').style.display='none'">&times;</span>
            <h4><fmt:message key="zlagoda.check.searchByEmployeeSurnamePeriod" bundle="${rb}"/></h4>
        </div>
        <div class="modal-body">
            <form action="${pageContext.request.contextPath}/controller/manager/checks/searchByEmployeeSurnamePerPeriod" method="POST">
                <label><fmt:message key="zlagoda.surname" bundle="${rb}"/></label><br>
                <input type="text" name="surname" style="width: 100%; padding: 8px;"><br><br>
                <label><fmt:message key="zlagoda.date.from" bundle="${rb}"/></label><br>
                <input type="date" name="fromDate" style="width: 100%; padding: 8px;"><br><br>
                <label><fmt:message key="zlagoda.date.to" bundle="${rb}"/></label><br>
                <input type="date" name="toDate" style="width: 100%; padding: 8px;"><br><br>
                <div class="modal-footer">
                    <button class="btn" type="submit"><fmt:message key="zlagoda.search" bundle="${rb}"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="modalChecksPerPeriod" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" onclick="document.getElementById('modalChecksPerPeriod').style.display='none'">&times;</span>
            <h4><fmt:message key="zlagoda.check.searchPerPeriod" bundle="${rb}"/></h4>
        </div>
        <div class="modal-body">
            <form action="${pageContext.request.contextPath}/controller/manager/checks/searchPerPeriod" method="POST">
                <label><fmt:message key="zlagoda.date.from" bundle="${rb}"/></label><br>
                <input type="date" name="fromDate" style="width: 100%; padding: 8px;"><br><br>
                <label><fmt:message key="zlagoda.date.to" bundle="${rb}"/></label><br>
                <input type="date" name="toDate" style="width: 100%; padding: 8px;"><br><br>
                <div class="modal-footer">
                    <button class="btn" type="submit"><fmt:message key="zlagoda.search" bundle="${rb}"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="modalSumBySurnamePeriod" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" onclick="document.getElementById('modalSumBySurnamePeriod').style.display='none'">&times;</span>
            <h4><fmt:message key="zlagoda.check.searchSumBySurnamePeriod" bundle="${rb}"/></h4>
        </div>
        <div class="modal-body">
            <form action="${pageContext.request.contextPath}/controller/manager/checks/searchSumByEmployeeSurnamePerPeriod" method="POST">
                <label><fmt:message key="zlagoda.surname" bundle="${rb}"/></label><br>
                <input type="text" name="surname" style="width: 100%; padding: 8px;"><br><br>
                <label><fmt:message key="zlagoda.date.from" bundle="${rb}"/></label><br>
                <input type="date" name="fromDate" style="width: 100%; padding: 8px;"><br><br>
                <label><fmt:message key="zlagoda.date.to" bundle="${rb}"/></label><br>
                <input type="date" name="toDate" style="width: 100%; padding: 8px;"><br><br>
                <div class="modal-footer">
                    <button class="btn" type="submit"><fmt:message key="zlagoda.search" bundle="${rb}"/></button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="modalSumPerPeriod" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" onclick="document.getElementById('modalSumPerPeriod').style.display='none'">&times;</span>
            <h4><fmt:message key="zlagoda.check.searchSumPerPeriod" bundle="${rb}"/></h4>
        </div>
        <div class="modal-body">
            <form action="${pageContext.request.contextPath}/controller/manager/checks/searchSumPerPeriod" method="POST">
                <label><fmt:message key="zlagoda.date.from" bundle="${rb}"/></label><br>
                <input type="date" name="fromDate" style="width: 100%; padding: 8px;"><br><br>
                <label><fmt:message key="zlagoda.date.to" bundle="${rb}"/></label><br>
                <input type="date" name="toDate" style="width: 100%; padding: 8px;"><br><br>
                <div class="modal-footer">
                    <button class="btn" type="submit"><fmt:message key="zlagoda.search" bundle="${rb}"/></button>
                </div>
            </form>
        </div>
    </div>
</div>



<div style="text-align: left; margin-bottom: 15px;">
    <form action="${pageContext.request.contextPath}/controller/manager" method="get">
        <button type="submit"
                style="background-color: #ff9800; color: white; padding: 10px 20px; border: none;
                       border-radius: 5px; cursor: pointer; font-weight: bold;">
            Back to manager menu
        </button>
    </form>
</div>



<h2><fmt:message key="zlagoda.checks" bundle="${rb}" /></h2>

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
        <th><fmt:message key="zlagoda.print_date" bundle="${rb}" /></th>
        <th><fmt:message key="zlagoda.sum_total" bundle="${rb}" /></th>
        <th><fmt:message key="zlagoda.vat" bundle="${rb}" /></th>
        <th><fmt:message key="zlagoda.employee" bundle="${rb}" /></th>
        <th><fmt:message key="zlagoda.customer_card" bundle="${rb}" /></th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${checks}" var="check" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${check.getPrint_date()}</td>
            <td>${check.getSum_total()}</td>
            <td>${check.getVat()}</td>
            <td>${check.getEmployee().getSurname()}</td>
            <td>${check.getCustomer_card().getSurname()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@include file="footer.jsp"%>
