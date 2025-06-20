<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Zlagoda</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background-color: #ecf0f1;
            font-family: Arial, sans-serif;
        }

        .main-content {
            display: flex;
            justify-content: center;
            align-items: center;
            height: calc(100vh - 220px);
            text-align: center;
        }

        .main-content h1 {
            font-size: 36px;
            color: #2c3e50;
            margin: 0;
        }
    </style>
</head>
<body>

<%@include file="WEB-INF/views/header.jsp"%>

<div class="main-content">
    <h1>
        <fmt:message key="zlagoda.greeting" bundle="${rb}" />
    </h1>
</div>

<%@include file="WEB-INF/views/footer.jsp"%>

</body>
</html>
