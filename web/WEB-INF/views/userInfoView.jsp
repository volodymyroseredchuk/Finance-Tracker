<%--
  Created by IntelliJ IDEA.
  User: Volodymyr
  Date: 4/5/2019
  Time: 4:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Info</title>
</head>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
    <jsp:include page="/WEB-INF/views/navbar.jsp"></jsp:include>

    <h3>User Information Page</h3>
    <p style="color: red;">${errorString}</p>

    UserName: <b>${user.getUserName()}</b>
    <br/>
    Role: <b>${user.getRole()}</b>
    <br/>
    Balance: <b>${user.getBalance()}</b>
    <br/><br/>

    <button type="button" value="Change Password" name="btnChangePassword" onclick="changePassword()">Change Password</button>
    <button type="button" value="Delete Account" name="btnDeleteAccount" onclick="deleteAccount()">Delete Account</button>
    <br/><br/>

<script>
    function changePassword()
    {
        // second button click will hide the form
        if(document.getElementById("hiddenChangePasswordForm").style.display === "block")
        {
            document.getElementById("hiddenChangePasswordForm").style.display = "none";
            return;
        }

        // only one of two choices can be available at the same moment
        if(document.getElementById("hiddenDeleteAccountForm").style.display === "block")
        {
            document.getElementById("hiddenDeleteAccountForm").style.display = "none"
        }
        document.getElementById("hiddenChangePasswordForm").style.display = "block";
    }

    function deleteAccount()
    {
        // second button click will hide the form
        if(document.getElementById("hiddenDeleteAccountForm").style.display === "block")
        {
            document.getElementById("hiddenDeleteAccountForm").style.display = "none";
            return;
        }

        // only one of two choices can be available at the same moment
        if(document.getElementById("hiddenChangePasswordForm").style.display === "block")
        {
            document.getElementById("hiddenChangePasswordForm").style.display = "none"
        }
        document.getElementById("hiddenDeleteAccountForm").style.display = "block";
    }
</script>

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

    <div id="hiddenDeleteAccountForm" style="display: none;">
        <form method="post" action="${pageContext.request.contextPath}/deleteAccount">
            <input type="hidden" name="username" value="${user.getUserName()}">
            Confirm deleting account with password:<br>
            <input type="password" name="password"><br><br>
            <input type="submit" value="Submit">
            <a href="${pageContext.request.contextPath}/userInfo">Cancel</a>
        </form>
    </div>

    <jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
</body>
</html>
