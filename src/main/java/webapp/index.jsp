<%@include file="WEB-INF/views/header.jsp"%>

<div class="container-fluid">

    <div class="row-fluid" align="left">
        <div class="btn-group" role="group" aria-label="buttons">
            <button type="button" class="btn btn-default"
                    onclick="location.href='${pageContext.request.contextPath}/controller/manager/categories';">
                <fmt:message key="zlagoda.greeting" bundle="${rb}" /> (Тест БД)
            </button>
            <button type="button" class="btn btn-default" data-toggle="modal"
                    data-target="#searchByName">
                <fmt:message key="zlagoda.category.searchByName" bundle="${rb}" />
            </button>
        </div>
    </div>

    <!-- modal searchByName -->
    <div class="modal fade" id="searchByName" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        <fmt:message key="zlagoda.category.searchByName" bundle="${rb}" />
                    </h4>
                </div>
                <div class="modal-body">
                    <form
                            action="${pageContext.request.contextPath}/controller/manager/categories/name"
                            method="POST" role="form">

                        <div class="form-group">
                            <label for="name"><fmt:message
                                    key="zlagoda.category.name" bundle="${rb}" /></label> <input
                                type="text" class="form-control" id="name" name="name" />
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-default" id="submitButton">
                                <fmt:message key="zlagoda.search" bundle="${rb}" />
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="row-fluid" align="center">
        <h2>
            <fmt:message key="zlagoda.greeting" bundle="${rb}" /> - Тест підключення до БД
        </h2>
    </div>

    <div class="row-fluid" align="center">
        <c:if test="${not empty param.success}">
            <div class="alert alert-success">
                <fmt:message key="${param.success}" bundle="${rb}" />
            </div>
        </c:if>
    </div>
    <div class="row-fluid" align="center">
        <c:if test="${not empty param.error}">
            <div class="alert alert-danger">
                <fmt:message key="${param.error}" bundle="${rb}" />
            </div>
        </c:if>
    </div>

    <!-- Статус підключення до БД -->
    <div class="row-fluid" align="center">
        <c:choose>
            <c:when test="${not empty categories}">
                <div class="alert alert-success">
                    <strong>✓ Підключення до БД працює!</strong>
                    Завантажено ${fn:length(categories)} категорій з бази даних.
                </div>
            </c:when>
            <c:when test="${empty categories and not empty categoriesRequested}">
                <div class="alert alert-warning">
                    <strong>⚠ БД підключена, але категорій не знайдено.</strong>
                    Таблиця категорій може бути пустою.
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-info">
                    <strong>ℹ Натисніть кнопку "Тест БД" для перевірки підключення до бази даних.</strong>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Таблиця категорій (якщо є) -->
    <c:if test="${not empty categories}">
        <div class="row-fluid top-margin" align="center">
            <h3><fmt:message key="zlagoda.categories" bundle="${rb}" /></h3>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>#</th>
                    <th><fmt:message key="zlagoda.name" bundle="${rb}" /></th>
                    <th>Дії</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${categories}" var="category" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${category.getName()}</td>
                        <td>
                            <small class="text-muted">ID: ${category.getId()}</small>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

</div>

<%@include file="WEB-INF/views/footer.jsp"%>