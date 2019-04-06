<%--
  Created by IntelliJ IDEA.
  User: Volodymyr
  Date: 4/6/2019
  Time: 12:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Cash Flow</title>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
    <jsp:include page="/WEB-INF/views/navbar.jsp"></jsp:include>

    <c:if test="${not empty deleteProfitSuccess}">
        <script>
            window.alert("Profit was deleted successfully!");
        </script>
    </c:if>

    <c:if test="${not empty deleteSpendingSuccess}">
        <script>
            window.alert("Spending was deleted successfully!");
        </script>
    </c:if>

    <script>
        function addProfit()
        {
            // second button click will hide the form
            if(document.getElementById("hiddenAddProfit").style.display === "block")
            {
                document.getElementById("hiddenAddProfit").style.display = "none";
            }
            else
            {
                document.getElementById("hiddenAddProfit").style.display = "block";
            }
        }

        function addSpending()
        {
            // second button click will hide the form
            if(document.getElementById("hiddenAddSpending").style.display === "block")
            {
                document.getElementById("hiddenAddSpending").style.display = "none";
            }
            else
            {
                document.getElementById("hiddenAddSpending").style.display = "block";
            }
        }
    </script>

    <h3>Cash Flow Information Page</h3>
    <p style="color: red;">${errorString}</p>

    <div style="float: left; width: 50%;">
        <caption><h1 style="text-align: center">Profits</h1></caption>
        <table>
            <tr>
                <th style="width: 50%; text-align: left">Description</th>
                <th style="width: 25%; text-align: left">Category</th>
                <th style="width: 15%; text-align: left">Date</th>
                <th style="width: 15%; text-align: left">Value</th>
                <th style="width: 10%; text-align: left">Edit</th>
                <th style="width: 10%; text-align: left">Delete</th>
            </tr>

            <c:forEach items="${profitsList}" var="profit">
                <tr>
                    <td>${profit.getDescription()}</td>
                    <td>${profit.getCategory()}</td>
                    <td>${profit.getCreationDate()}</td>
                    <td>${profit.getValue()}</td>
                    <td>
                        <button type="button" value="Edit" name="btnProfitEdit" onclick="">Edit</button>
                    </td>
                    <td>
                        <button type="button" value="Delete" name="btnProfitDelete" onclick="">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>

        </br>
        <button type="button" value="AddProfit" name="btnProfitAdd" onclick="addProfit()">Add profit</button><br/><br/>

        <div id="hiddenAddProfit" style="display: none;">
            <form method="post" action="${pageContext.request.contextPath}/addCashFlow">
                <input type="hidden" name="isProfit" value="true">
                Description:<br>
                <input type="text" name="description"><br>
                Category:<br>
                <input type="text" name="category"><br>
                Value:<br>
                <input type="number" step="0.01" name="value"><br><br>
                <input type="submit" value="Submit">
                <a href="${pageContext.request.contextPath}/cashFlow">Cancel</a>
                <br/><br/>
            </form>
        </div>
    </div>

    <div style="float: left; width: 50%;">
        <caption><h1 style="text-align: center">Spending</h1></caption>
        <table>
            <tr>
                <th style="width: 50%; text-align: left">Description</th>
                <th style="width: 25%; text-align: left">Category</th>
                <th style="width: 15%; text-align: left">Date</th>
                <th style="width: 15%; text-align: left">Value</th>
                <th style="width: 10%; text-align: left">Edit</th>
                <th style="width: 10%; text-align: left">Delete</th>
            </tr>

            <c:forEach items="${spendingList}" var="spending">
                <tr>
                    <td>${spending.getDescription()}</td>
                    <td>${spending.getCategory()}</td>
                    <td>${spending.getCreationDate()}</td>
                    <td>${spending.getValue()}</td>
                    <td>
                        <button type="button" value="Edit" name="btnSpendingEdit" onclick="">Edit</button>
                    </td>
                    <td>
                        <button type="button" value="Delete" name="btnSpendingDelete" onclick="">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>

        </br>
        <button type="button" value="AddSpending" name="btnSpendingAdd" onclick="addSpending()">Add spending</button><br/><br/>

        <div id="hiddenAddSpending" style="display: none;">
            <form method="post" action="${pageContext.request.contextPath}/addCashFlow">
                <input type="hidden" name="isProfit" value="false">
                Description:<br>
                <input type="text" name="description"><br>
                Category:<br>
                <input type="text" name="category"><br>
                Value:<br>
                <input type="number" step="0.01" name="value"><br><br>
                <input type="submit" value="Submit">
                <a href="${pageContext.request.contextPath}/cashFlow">Cancel</a>
                <br/><br/>
            </form>
        </div>
    </div>

    <jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
</body>
</html>
