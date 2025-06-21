<%@include file="/WEB-INF/views/header.jsp"%>

<div class="container-fluid" align="center">

    <div class="row-fluid pg-title">
        <h3 style="margin-top: 20px; color: #00264d;">
            <c:choose>
                <c:when test="${not empty requestScope.category.getId()}">
                    <fmt:message key="zlagoda.update" bundle="${rb}" />
                </c:when>
                <c:otherwise>
                    <fmt:message key="zlagoda.add" bundle="${rb}" />
                </c:otherwise>
            </c:choose>
        </h3>
    </div>

    <div class="row-fluid">
        <div class="col-sm-6 col-sm-offset-3">
            <c:choose>
            <c:when test="${not empty requestScope.category.getId()}">
            <form action="${pageContext.request.contextPath}/controller/manager/categories/updateCategory" method="POST">
                </c:when>
                <c:otherwise>
                <form action="${pageContext.request.contextPath}/controller/manager/categories/addCategory" method="POST">
                    </c:otherwise>
                    </c:choose>

                    <c:if test="${not empty requestScope.errors}">
                        <div class="alert alert-danger" style="margin-top: 15px;">
                            <c:forEach items="${requestScope.errors}" var="error">
                                <p><fmt:message key="${error}" bundle="${rb}" /></p>
                            </c:forEach>
                        </div>
                    </c:if>

                    <div class="form-group" style="margin-top: 20px;">
                        <label for="category_name" style="font-weight: bold;">
                            <fmt:message key="zlagoda.category.name" bundle="${rb}" />
                        </label>
                        <input type="text" class="form-control" id="category_name" name="category_name"
                               placeholder="<fmt:message key='zlagoda.category.name' bundle='${rb}'/>"
                               value="<c:out value='${requestScope.category.getName()}'/>" required/>
                    </div>

                    <c:if test="${not empty requestScope.category.getId()}">
                        <input type="hidden" id="category_number" name="category_number"
                               value="<c:out value='${requestScope.category.getId()}'/>">
                    </c:if>

                    <div style="margin-top: 20px;">
                        <button type="submit" class="btn btn-primary">
                            <c:choose>
                                <c:when test="${not empty requestScope.category.getId()}">
                                    <fmt:message key="zlagoda.update" bundle="${rb}" />
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="zlagoda.add" bundle="${rb}" />
                                </c:otherwise>
                            </c:choose>
                        </button>

                        <a href="${pageContext.request.contextPath}/controller/manager/categories"
                           class="btn btn-danger" style="margin-left: 10px;">
                            <fmt:message key="zlagoda.cancel" bundle="${rb}" />
                        </a>
                    </div>

                </form>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/views/footer.jsp"%>

