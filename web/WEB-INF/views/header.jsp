<%--
  Created by IntelliJ IDEA.
  User: Volodymyr
  Date: 4/3/2019
  Time: 8:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div style="background-color: papayawhip; height: 60px; padding: 5px;">
    <div style="float:left">
        <h1>Finance Tracker</h1>
    </div>

    <div style="float: right; padding: 10px; text-align: right;">

        <!-- User stored in session with attribute: loginedUser -->
        <c:if test="${not empty loginedUser}">
            Hello, <b>${loginedUser.userName}</b>
            <br/>
            <a href="${pageContext.request.contextPath}/logout" style="text-underline-color: lime;">Log out</a>
        </c:if>

    </div>
</div>