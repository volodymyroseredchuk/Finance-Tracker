<%--
  Created by IntelliJ IDEA.
  User: Volodymyr
  Date: 4/3/2019
  Time: 8:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
    <jsp:include page="/WEB-INF/views/navbar.jsp"></jsp:include>

    <h3>Welcome to my Finance Tracker Application</h3>
    <b>It includes the following functions:</b>
    <ul>
        <li>Log in or Sign up</li>
        <li>Manage cash flow</li>
        <li>Create, edit and delete profits and spending</li>
    </ul>

    <jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
</body>
</html>
