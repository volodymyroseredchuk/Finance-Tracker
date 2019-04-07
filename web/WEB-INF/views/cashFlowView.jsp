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
        #tableProfits tr > *:nth-child(1) {
            display: none;
        }
        #tableSpending tr > *:nth-child(1) {
            display: none;
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
            document.getElementById("hiddenAddProfit").style.display = "block";
        }

        function addProfitCancel()
        {
            document.getElementById("hiddenAddProfit").style.display = "none";

            document.getElementById("hiddenAddProfitDescription").setAttribute("value", "");
            document.getElementById("hiddenAddProfitCategory").setAttribute("value", "");
            document.getElementById("hiddenAddProfitValue").setAttribute("value", "");
        }

        function addSpending()
        {
            document.getElementById("hiddenAddSpending").style.display = "block";
        }

        function addSpendingCancel()
        {
            document.getElementById("hiddenAddSpending").style.display = "none";

            document.getElementById("hiddenAddSpendingDescription").setAttribute("value", "");
            document.getElementById("hiddenAddSpendingCategory").setAttribute("value", "");
            document.getElementById("hiddenAddSpendingValue").setAttribute("value", "");
        }

        function editProfit(ID, description, category, value)
        {
            document.getElementById("hiddenEditProfit").style.display = "block";

            document.getElementById("hiddenEditProfitID").setAttribute("value", ID);
            document.getElementById("hiddenEditProfitDescription").setAttribute("value", description);
            document.getElementById("hiddenEditProfitCategory").setAttribute("value", category);
            document.getElementById("hiddenEditProfitValue").setAttribute("value", value);
        }

        function editProfitCancel()
        {
            document.getElementById("hiddenEditProfit").style.display = "none";
        }

        function editSpending(ID, description, category, value)
        {
            document.getElementById("hiddenEditSpending").style.display = "block";

            document.getElementById("hiddenEditSpendingID").setAttribute("value", ID);
            document.getElementById("hiddenEditSpendingDescription").setAttribute("value", description);
            document.getElementById("hiddenEditSpendingCategory").setAttribute("value", category);
            document.getElementById("hiddenEditSpendingValue").setAttribute("value", value);
        }

        function editSpendingCancel()
        {
            document.getElementById("hiddenEditSpending").style.display = "none";
        }
    </script>

    <h3>Cash Flow Information Page</h3>
    <p style="color: red;">${errorString}</p>

    <div style="float: left; width: 50%;">
        <caption><h1 style="text-align: center">Profits</h1></caption>
        <table id="tableProfits">
            <tr>
                <th>ID</th>
                <th style="width: 50%; text-align: left">Description</th>
                <th style="width: 25%; text-align: left">Category</th>
                <th style="width: 15%; text-align: left">Date</th>
                <th style="width: 15%; text-align: left">Value</th>
                <th style="width: 10%; text-align: left">Edit</th>
                <th style="width: 10%; text-align: left">Delete</th>
            </tr>

            <c:forEach items="${profitsList}" var="profit">
                <tr>
                    <td>${profit.getID()}</td>
                    <td>${profit.getDescription()}</td>
                    <td>${profit.getCategory()}</td>
                    <td>${profit.getCreationDate()}</td>
                    <td>${profit.getValue()}</td>
                    <td>
                        <button type="button" value="Edit" name="btnProfitEdit" onclick="editProfit(${profit.getID()},
                                '${profit.getDescription()}', '${profit.getCategory()}', ${profit.getValue()})">Edit</button>
                    </td>
                    <td>
                        <button type="button" value="Delete" name="btnProfitDelete" onclick="">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <br/>
        <button type="button" value="AddProfit" name="btnProfitAdd" onclick="addProfit()">Add profit</button><br/><br/>

        <div id="hiddenAddProfit" style="display: none;">
            <form method="post" action="${pageContext.request.contextPath}/addCashFlow">
                <input type="hidden" name="isProfit" value="true">
                Description:<br>
                <input type="text" name="description" value="" id="hiddenAddProfitDescription"><br>
                Category:<br>
                <input type="text" name="category" value="" id="hiddenAddProfitCategory"><br>
                Value:<br>
                <input type="number" step="0.01" name="value" value="" id="hiddenAddProfitValue"><br><br>
                <input type="submit" value="Submit">
                <button type="button" value="Cancel" name="btnAddProfitCancel" onclick="addProfitCancel()">Cancel</button>
                <br/><br/>
            </form>
        </div>

        <div id="hiddenEditProfit" style="display: none">
            <form method="post" action="${pageContext.request.contextPath}/editCashFlow">
                <input type="hidden" name="isProfit" value="true">
                <input type="hidden" name="ID" value="" id="hiddenEditProfitID">
                Description:<br>
                <input type="text" name="description" value="" id="hiddenEditProfitDescription"><br>
                Category:<br>
                <input type="text" name="category" value="" id="hiddenEditProfitCategory"><br>
                Value:<br>
                <input type="number" step="0.01" name="value" value="" id="hiddenEditProfitValue"><br><br>
                <input type="submit" value="Submit">
                <button type="button" value="Cancel" name="btnEditProfitCancel" onclick="editProfitCancel()">Cancel</button>
                <br/><br/>
            </form>
        </div>
    </div>

    <div style="float: left; width: 50%;">
        <caption><h1 style="text-align: center">Spending</h1></caption>
        <table id="tableSpending">
            <tr>
                <th>ID</th>
                <th style="width: 50%; text-align: left">Description</th>
                <th style="width: 25%; text-align: left">Category</th>
                <th style="width: 15%; text-align: left">Date</th>
                <th style="width: 15%; text-align: left">Value</th>
                <th style="width: 10%; text-align: left">Edit</th>
                <th style="width: 10%; text-align: left">Delete</th>
            </tr>

            <c:forEach items="${spendingList}" var="spending">
                <tr>
                    <td>${spending.getID()}</td>
                    <td>${spending.getDescription()}</td>
                    <td>${spending.getCategory()}</td>
                    <td>${spending.getCreationDate()}</td>
                    <td>${spending.getValue()}</td>
                    <td>
                        <button type="button" value="Edit" name="btnSpendingEdit" onclick="editSpending(${spending.getID()},
                                '${spending.getDescription()}', '${spending.getCategory()}', ${spending.getValue()})">Edit</button>
                    </td>
                    <td>
                        <button type="button" value="Delete" name="btnSpendingDelete" onclick="">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <br/>
        <button type="button" value="AddSpending" name="btnSpendingAdd" onclick="addSpending()">Add spending</button><br/><br/>

        <div id="hiddenAddSpending" style="display: none;">
            <form method="post" action="${pageContext.request.contextPath}/addCashFlow">
                <input type="hidden" name="isProfit" value="false">
                Description:<br>
                <input type="text" name="description" value="" id="hiddenAddSpendingDescription"><br>
                Category:<br>
                <input type="text" name="category" value="" id="hiddenAddSpendingCategory"><br>
                Value:<br>
                <input type="number" step="0.01" name="value" value="" id="hiddenAddSpendingValue"><br><br>
                <input type="submit" value="Submit">
                <button type="button" value="Cancel" name="btnAddSpendingCancel" onclick="addSpendingCancel()">Cancel</button>
                <br/><br/>
            </form>
        </div>

        <div id="hiddenEditSpending" style="display: none">
            <form method="post" action="${pageContext.request.contextPath}/editCashFlow">
                <input type="hidden" name="isProfit" value="false">
                <input type="hidden" name="ID" value="" id="hiddenEditSpendingID">
                Description:<br>
                <input type="text" name="description" value="" id="hiddenEditSpendingDescription"><br>
                Category:<br>
                <input type="text" name="category" value="" id="hiddenEditSpendingCategory"><br>
                Value:<br>
                <input type="number" step="0.01" name="value" value="" id="hiddenEditSpendingValue"><br><br>
                <input type="submit" value="Submit">
                <button type="button" value="Cancel" name="btnEditSpendingCancel" onclick="editSpendingCancel()">Cancel</button>
                <br/><br/>
            </form>
        </div>
    </div>

    <jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
</body>
</html>
