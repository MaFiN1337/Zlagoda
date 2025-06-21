<%@include file="/WEB-INF/views/header.jsp"%>

<div class="container-fluid" align="center">

    <div class="row-fluid pg-title">
        <h3 style="margin-top: 20px; color: #00264d;">
            <c:choose>
                <c:when test="${not empty requestScope.employee.getId()}">
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
            <c:when test="${not empty requestScope.employee.getId()}">
            <form action="${pageContext.request.contextPath}/controller/manager/employees/updateEmployee" method="POST">
                </c:when>
                <c:otherwise>
                <form action="${pageContext.request.contextPath}/controller/manager/employees/addEmployee" method="POST">
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
                                <label for="empl_name" style="font-weight: bold;">
                                    <fmt:message key="zlagoda.employee.name" bundle="${rb}" />
                                </label>
                                <input type="text" class="form-control" id="empl_name" name="empl_name"
                                       placeholder="<fmt:message key='zlagoda.employee.name' bundle='${rb}'/>"
                                       value="<c:out value='${requestScope.employee.getName()}'/>" required/>
                            </div>
                            <div class="mb-3">
                                <label for="empl_surname" style="font-weight: bold;">
                                    <fmt:message key="zlagoda.employee.surname" bundle="${rb}" />
                                </label>
                                <input type="text" class="form-control" id="empl_surname" name="empl_surname"
                                       placeholder="<fmt:message key='zlagoda.employee.surname' bundle='${rb}'/>"
                                       value="<c:out value='${requestScope.employee.getSurname()}'/>" required/>
                            </div>
                            <div class="mb-3">
                                <label for="empl_patronymic" style="font-weight: bold;">
                                    <fmt:message key="zlagoda.employee.patronymic" bundle="${rb}" />
                                </label>
                                <input type="text" class="form-control" id="empl_patronymic" name="empl_patronymic"
                                       placeholder="<fmt:message key='zlagoda.employee.patronymic' bundle='${rb}'/>"
                                       value="<c:out value='${requestScope.employee.getPatronymic()}'/>"/>
                            </div>
                            <div class="mb-3">
                                <label for="empl_role" style="font-weight: bold;">
                                    <fmt:message key="zlagoda.employee.role" bundle="${rb}" />
                                </label>
                                <input type="text" class="form-control" id="empl_role" name="empl_role"
                                       placeholder="<fmt:message key='zlagoda.employee.role' bundle='${rb}'/>"
                                       value="<c:out value='${requestScope.employee.getRole().getValue()}'/>" required/>
                            </div>
                            <div class="mb-3">
                                <label for="salary" style="font-weight: bold;">
                                    <fmt:message key="zlagoda.employee.salary" bundle="${rb}" />
                                </label>
                                <input type="text" class="form-control" id="salary" name="salary"
                                       placeholder="<fmt:message key='zlagoda.employee.salary' bundle='${rb}'/>"
                                       value="<c:out value='${requestScope.employee.getSalary()}'/>" required/>
                            </div>
                            <div class="mb-3">
                                <label for="date_of_birth" style="font-weight: bold;">
                                    <fmt:message key="zlagoda.employee.date_of_birth" bundle="${rb}" />
                                </label>
                                <input type="text" class="form-control" id="date_of_birth" name="date_of_birth"
                                       placeholder="<fmt:message key='zlagoda.employee.date_of_birth' bundle='${rb}'/>"
                                       value="<c:out value='${requestScope.employee.getDate_of_birth()}'/>" required/>
                            </div>
                            <div class="mb-3">
                                <label for="date_of_start" style="font-weight: bold;">
                                    <fmt:message key="zlagoda.employee.date_of_start" bundle="${rb}" />
                                </label>
                                <input type="text" class="form-control" id="date_of_start" name="date_of_start"
                                       placeholder="<fmt:message key='zlagoda.employee.date_of_start' bundle='${rb}'/>"
                                       value="<c:out value='${requestScope.employee.getDate_of_start()}'/>" required/>
                            </div>
                            <div class="mb-3">
                                <label for="phone_number" style="font-weight: bold;">
                                    <fmt:message key="zlagoda.employee.phone_number" bundle="${rb}" />
                                </label>
                                <input type="text" class="form-control" id="phone_number" name="phone_number"
                                       placeholder="<fmt:message key='zlagoda.employee.phone_number' bundle='${rb}'/>"
                                       value="<c:out value='${requestScope.employee.getPhone()}'/>" required/>
                            </div>
                            <div class="mb-3">
                                <label for="city" style="font-weight: bold;">
                                    <fmt:message key="zlagoda.employee.city" bundle="${rb}" />
                                </label>
                                <input type="text" class="form-control" id="city" name="city"
                                       placeholder="<fmt:message key='zlagoda.employee.city' bundle='${rb}'/>"
                                       value="<c:out value='${requestScope.employee.getCity()}'/>" required/>
                            </div>
                            <div class="mb-3">
                                <label for="street" style="font-weight: bold;">
                                    <fmt:message key="zlagoda.employee.street" bundle="${rb}" />
                                </label>
                                <input type="text" class="form-control" id="street" name="street"
                                       placeholder="<fmt:message key='zlagoda.employee.street' bundle='${rb}'/>"
                                       value="<c:out value='${requestScope.employee.getStreet()}'/>" required/>
                            </div>
                            <div class="mb-3">
                                <label for="zip_code" style="font-weight: bold;">
                                    <fmt:message key="zlagoda.employee.zip_code" bundle="${rb}" />
                                </label>
                                <input type="text" class="form-control" id="zip_code" name="zip_code"
                                       placeholder="<fmt:message key='zlagoda.employee.zip_code' bundle='${rb}'/>"
                                       value="<c:out value='${requestScope.employee.getZip_code()}'/>" required/>
                            </div>
                        </div>

                    <c:if test="${not empty requestScope.employee.getId()}">
                        <input type="hidden" id="id_employee" name="id_employee"
                               value="<c:out value='${requestScope.employee.getId()}'/>">
                    </c:if>

                    <div style="margin-top: 20px;">
                        <button type="submit" class="btn btn-primary">
                            <c:choose>
                                <c:when test="${not empty requestScope.employee.getId()}">
                                    <fmt:message key="zlagoda.update" bundle="${rb}" />
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="zlagoda.add" bundle="${rb}" />
                                </c:otherwise>
                            </c:choose>
                        </button>

                        <a href="${pageContext.request.contextPath}/controller/manager/employees"
                           class="btn btn-danger" style="margin-left: 10px;">
                            <fmt:message key="zlagoda.cancel" bundle="${rb}" />
                        </a>
                    </div>

                </form>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/views/footer.jsp"%>


