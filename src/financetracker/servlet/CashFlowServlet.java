package financetracker.servlet;

import financetracker.beans.CashFlow;
import financetracker.beans.UserAccount;
import financetracker.manager.DatabaseQueryManager;
import financetracker.manager.TemporaryStoringManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CashFlowServlet", urlPatterns = {"/cashFlow"})
public class CashFlowServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    // show cash flow information page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = TemporaryStoringManager.getStoredConnection(request);
        UserAccount user = TemporaryStoringManager.getStoredLoginedUser(request.getSession());
        String errorString = null;
        List<CashFlow> profitsList = null;
        List<CashFlow> spendingList = null;

        // get lists from database
        try
        {
            profitsList = DatabaseQueryManager.queryCashFlow(connection, user.getUserID(), true);
            spendingList = DatabaseQueryManager.queryCashFlow(connection, user.getUserID(), false);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        // store lists and errorString in request attributes
        request.setAttribute("errorString", errorString);
        request.setAttribute("profitsList", profitsList);
        request.setAttribute("spendingList", spendingList);

        // forward to cashFlowView.jsp
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/cashFlowView.jsp");
        dispatcher.forward(request, response);
    }
}
