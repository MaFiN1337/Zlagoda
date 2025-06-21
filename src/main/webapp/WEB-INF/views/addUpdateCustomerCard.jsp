<%@include file="/WEB-INF/views/header.jsp"%>

<div class="container-fluid" align="center">

    <div class="row-fluid pg-title">
        <h3 style="margin-top: 20px; color: #00264d;">
            <c:choose>
                <c:when test="${not empty requestScope.customer_card.getNumber()}">
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
            <c:when test="${not empty requestScope.customer_card.getNumber()}">
            <form action="${pageContext.request.contextPath}/controller/manager/customerCards/updateCustomerCard" method="POST">
                </c:when>
                <c:otherwise>
                <form action="${pageContext.request.contextPath}/controller/manager/customerCards/addCustomerCard" method="POST">
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
                        <div class="mb-3">
                            <label for="cust_name" style="font-weight: bold;">
                                <fmt:message key="zlagoda.customer_card.name" bundle="${rb}" />
                            </label>
                            <input type="text" class="form-control" id="cust_name" name="cust_name"
                                   placeholder="<fmt:message key='zlagoda.customer_card.name' bundle='${rb}'/>"
                                   value="<c:out value='${requestScope.customer_card.getName()}'/>" required/>
                        </div>
                        <div class="mb-3">
                            <label for="cust_surname" style="font-weight: bold;">
                                <fmt:message key="zlagoda.customer_card.surname" bundle="${rb}" />
                            </label>
                            <input type="text" class="form-control" id="cust_surname" name="cust_surname"
                                   placeholder="<fmt:message key='zlagoda.customer_card.surname' bundle='${rb}'/>"
                                   value="<c:out value='${requestScope.customer_card.getSurname()}'/>" required/>
                        </div>
                        <div class="mb-3">
                            <label for="cust_patronymic" style="font-weight: bold;">
                                <fmt:message key="zlagoda.customer_card.patronymic" bundle="${rb}" />
                            </label>
                            <input type="text" class="form-control" id="cust_patronymic" name="cust_patronymic"
                                   placeholder="<fmt:message key='zlagoda.customer_card.patronymic' bundle='${rb}'/>"
                                   value="<c:out value='${requestScope.customer_card.getPatronymic()}'/>"/>
                        </div>
                        <div class="mb-3">
                            <label for="percent" style="font-weight: bold;">
                                <fmt:message key="zlagoda.customer_card.percent" bundle="${rb}" />
                            </label>
                            <input type="text" class="form-control" id="percent" name="percent"
                                   placeholder="<fmt:message key='zlagoda.customer_card.percent' bundle='${rb}'/>"
                                   value="<c:out value='${requestScope.customer_card.getPercent()}'/>" required/>
                        </div>
                        <div class="mb-3">
                            <label for="phone_number" style="font-weight: bold;">
                                <fmt:message key="zlagoda.customer_card.phone_number" bundle="${rb}" />
                            </label>
                            <input type="text" class="form-control" id="phone_number" name="phone_number"
                                   placeholder="<fmt:message key='zlagoda.customer_card.phone_number' bundle='${rb}'/>"
                                   value="<c:out value='${requestScope.customer_card.getPhoneNumber()}'/>" required/>
                        </div>
                        <div class="mb-3">
                            <label for="city" style="font-weight: bold;">
                                <fmt:message key="zlagoda.customer_card.city" bundle="${rb}" />
                            </label>
                            <input type="text" class="form-control" id="city" name="city"
                                   placeholder="<fmt:message key='zlagoda.customer_card.city' bundle='${rb}'/>"
                                   value="<c:out value='${requestScope.customer_card.getCity()}'/>" required/>
                        </div>
                        <div class="mb-3">
                            <label for="street" style="font-weight: bold;">
                                <fmt:message key="zlagoda.customer_card.street" bundle="${rb}" />
                            </label>
                            <input type="text" class="form-control" id="street" name="street"
                                   placeholder="<fmt:message key='zlagoda.customer_card.street' bundle='${rb}'/>"
                                   value="<c:out value='${requestScope.customer_card.getStreet()}'/>" required/>
                        </div>
                        <div class="mb-3">
                            <label for="zip_code" style="font-weight: bold;">
                                <fmt:message key="zlagoda.customer_card.zip_code" bundle="${rb}" />
                            </label>
                            <input type="text" class="form-control" id="zip_code" name="zip_code"
                                   placeholder="<fmt:message key='zlagoda.customer_card.zip_code' bundle='${rb}'/>"
                                   value="<c:out value='${requestScope.customer_card.getZip_code()}'/>" required/>
                        </div>
                    </div>

                    <c:if test="${not empty requestScope.customer_card.getNumber()}">
                        <input type="hidden" id="card_number" name="card_number"
                               value="<c:out value='${requestScope.customer_card.getNumber()}'/>">
                    </c:if>

                    <div style="margin-top: 20px;">
                        <button type="submit" class="btn btn-primary">
                            <c:choose>
                                <c:when test="${not empty requestScope.customer_card.getNumber()}">
                                    <fmt:message key="zlagoda.update" bundle="${rb}" />
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="zlagoda.add" bundle="${rb}" />
                                </c:otherwise>
                            </c:choose>
                        </button>

                        <a href="${pageContext.request.contextPath}/controller/manager/customerCards"
                           class="btn btn-danger" style="margin-left: 10px;">
                            <fmt:message key="zlagoda.cancel" bundle="${rb}" />
                        </a>
                    </div>

                </form>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/views/footer.jsp"%>



