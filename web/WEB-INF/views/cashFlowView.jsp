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
    <script src="${pageContext.request.contextPath}/javascript/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/cashFlowFunctions.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/cashFlowJQuery.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cashFlowCSS.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/formsCSS.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbarCSS.css">

    <title>Cash Flow</title>
</head>

<body>
    <%-- include header and navigation bar --%>
    <jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
    <jsp:include page="/WEB-INF/views/navbar.jsp"></jsp:include>

    <h3>Cash Flow Information Page</h3>
    <p style="color: red;">${errorString}</p>

    <%-- Div-container that contains table with profits and containers that contain forms to add and edit profits--%>
    <div style="float: left; width: 50%; overflow-y: auto;">
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

            <%-- for each profit in list - table row will be created - with buttons to edit and delete that profit--%>
            <c:forEach items="${profitsList}" var="profit">
                <tr id="trProfit${profit.getID()}">
                    <td>${profit.getID()}</td>
                    <td>${profit.getDescription()}</td>
                    <td>${profit.getCategory()}</td>
                    <td>${profit.getCreationDate()}</td>
                    <td class="profitValue">${profit.getValue()}</td>
                    <td>
                        <button type="button" value="Edit" name="btnProfitEdit" class="tableBtns" onclick="editProfit(${profit.getID()},
                                '${profit.getDescription()}', '${profit.getCategory()}', ${profit.getValue()})">Edit</button>
                    </td>
                    <td>
                        <button type="button" value="Delete" name="btnProfitDelete" class="btnProfitDelete tableBtns" id="btnProfit${profit.getID()}">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <%-- button to show form that allows to add new profit --%>
        <br/>
        <button type="button" value="AddProfit" name="btnProfitAdd" class="addBtns" onclick="addProfit()">Add profit</button><br/><br/>

        <%-- hidden div-container that contains form to add new profit - appears on button click --%>
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

        <%-- hidden div-container that contains form to edit profit - appears on button click --%>
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

    <%-- Div-container that contains table with spending and containers that contain forms to add and edit spending--%>
    <div style="float: left; width: 50%;overflow-y: auto;">
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

            <%-- for each spending in list - table row will be created - with buttons to edit and delete that spending--%>
            <c:forEach items="${spendingList}" var="spending">
                <tr id="trSpending${spending.getID()}">
                    <td>${spending.getID()}</td>
                    <td>${spending.getDescription()}</td>
                    <td>${spending.getCategory()}</td>
                    <td>${spending.getCreationDate()}</td>
                    <td class="spendingValue">${spending.getValue()}</td>
                    <td>
                        <button type="button" value="Edit" class="tableBtns" name="btnSpendingEdit" onclick="editSpending(${spending.getID()},
                                '${spending.getDescription()}', '${spending.getCategory()}', ${spending.getValue()})">Edit</button>
                    </td>
                    <td>
                        <button type="button" value="Delete" name="btnSpendingDelete" class="btnSpendingDelete tableBtns" id="btnSpending${spending.getID()}">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <%-- button to show form that allows to add new spending --%>
        <br/>
        <button type="button" value="AddSpending" name="btnSpendingAdd" class="addBtns" onclick="addSpending()">Add spending</button><br/><br/>

        <%-- hidden div-container that contains form to add new spending - appears on button click --%>
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

        <%-- hidden div-container that contains form to edit profit - appears on button click --%>
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

    <%-- include footer --%>
    <jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
</body>
</html>
