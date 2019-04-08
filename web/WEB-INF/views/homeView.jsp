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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarCSS.css">

    <title>Home Page</title>
</head>
<body>
    <%-- include header and navigation bar --%>
    <jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
    <jsp:include page="/WEB-INF/views/navbar.jsp"></jsp:include>

    <h3>Welcome to my Finance Tracker Application</h3>
    <b>It includes the following functions:</b>
    <ul>
        <li>Log in or Sign up</li>
        <li>Manage cash flows</li>
        <li>View, create, edit and delete profits and spending</li>
        <li>Change password or delete account</li>
        <li>View user information and current balance</li>
    </ul>

    <%-- include footer --%>
    <jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
</body>
</html>
