<%--
  Created by IntelliJ IDEA.
  User: Volodymyr
  Date: 4/3/2019
  Time: 8:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div style="padding: 5px;">

    <ul id="navbarUL">
        <li><a href="${pageContext.request.contextPath}/" class="active">Home</a></li>

        <!-- User stored in session with attribute: loginedUser -->

        <!-- Display Log in and Sign up menu only for not logined users -->
        <c:if test="${empty loginedUser}">
            <li><a href="${pageContext.request.contextPath}/login">Log in</a></li>
            <li><a href="${pageContext.request.contextPath}/signup">Sign up</a></li>
        </c:if>

        <li><a href="${pageContext.request.contextPath}/userInfo">User Info</a></li>
        <li><a href="${pageContext.request.contextPath}/cashFlow">Cash Flow</a></li>
    </ul>
</div>
