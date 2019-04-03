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

    <a href="${pageContext.request.contextPath}/">Home</a>
    <a href="${pageContext.request.contextPath}/login">Log in</a>

    <!-- User stored in session with attribute: loginedUser -->
    <c:if test="${empty loginedUser}">
        <a href="${pageContext.request.contextPath}/signup">Sign up</a>
    </c:if>

</div>
