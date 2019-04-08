<%--
  Created by IntelliJ IDEA.
  User: Volodymyr
  Date: 4/5/2019
  Time: 4:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <script src="${pageContext.request.contextPath}/javascript/userInfoFunctions.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/formsCSS.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarCSS.css">

    <title>User Info</title>
</head>
<body>
    <%-- include header and navigation bar --%>
    <jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
    <jsp:include page="/WEB-INF/views/navbar.jsp"></jsp:include>

<c:if test="${not empty changePasswordSuccess}">
    <script>
        window.alert("Password was changed successfully!");
    </script>
</c:if>

    <h3>User Information Page</h3>
    <p style="color: red;">${errorString}</p>

    UserName: <b>${user.getUserName()}</b>
    <br/>
    Role: <b>${user.getRole()}</b>
    <br/>
    Balance: <b>${user.getBalance()}</b>
    <br/><br/>

    <%-- buttons to show hidden forms to change password or delete account ---%>
    <button type="button" value="Change Password" name="btnChangePassword" onclick="changePassword()">Change Password</button>
    <button type="button" value="Delete Account" name="btnDeleteAccount" onclick="deleteAccount()">Delete Account</button>
    <br/><br/>


    <%-- hidden div-container that contains form to change password - appears on button click--%>
    <div id="hiddenChangePasswordForm" style="display: none;">
        <form method="post" action="${pageContext.request.contextPath}/changePassword">
            <input type="hidden" name="username" value="${user.getUserName()}">
            Old Password:<br>
            <input type="password" name="oldPassword"><br>
            New Password:<br>
            <input type="password" name="newPassword"><br>
            Confirm Password:<br>
            <input type="password" name="confirmPassword"><br><br>
            <input type="submit" value="Submit">
            <a href="${pageContext.request.contextPath}/userInfo">Cancel</a>
            <br/><br/>
        </form>
    </div>

    <%-- hidden div-container that contains form to delete account - appears on button click--%>
    <div id="hiddenDeleteAccountForm" style="display: none;">
        <form method="post" action="${pageContext.request.contextPath}/deleteAccount">
            <input type="hidden" name="username" value="${user.getUserName()}">
            Confirm deleting account with password:<br>
            <input type="password" name="password"><br><br>
            <input type="submit" value="Submit">
            <a href="${pageContext.request.contextPath}/userInfo">Cancel</a>
        </form>
    </div>

    <%-- include footer --%>
    <jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
</body>
</html>
