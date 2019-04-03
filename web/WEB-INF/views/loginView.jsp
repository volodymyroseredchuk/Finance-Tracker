<%--
  Created by IntelliJ IDEA.
  User: Volodymyr
  Date: 4/3/2019
  Time: 8:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
    <jsp:include page="/WEB-INF/views/navbar.jsp"></jsp:include>
    <h3>Login Page</h3>
    <p style="color: red;">${errorString}</p>

    <form method="post" action="${pageContext.request.contextPath}/login">
        User Name:<br>
        <input type="text" name="username"><br>
        Password:<br>
        <input type="password" name="password"><br><br>
        <input type="submit" value="Submit">
        <a href="${pageContext.request.contextPath}/">Cancel</a>
    </form>
    <jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
</body>
</html>
