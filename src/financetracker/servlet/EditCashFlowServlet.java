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

@WebServlet(name = "EditCashFlowServlet", urlPatterns = {"/editCashFlow"})
public class EditCashFlowServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get input data
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        int ID = 0;
        double value = 0;
        boolean isProfit = false;

        String errorString = null;
        boolean hasError = false;
        CashFlow cashFlow = null;
        UserAccount user = TemporaryStoringManager.getStoredLoginedUser(request.getSession());
        Connection connection = TemporaryStoringManager.getStoredConnection(request);

        try
        {
            value = Double.parseDouble(request.getParameter("value"));
            ID = Integer.parseInt(request.getParameter("ID"));
            isProfit = Boolean.parseBoolean(request.getParameter("isProfit"));
        }
        catch (Exception e)
        {
            // set error type
            e.printStackTrace();
            hasError = true;
            errorString = "Incomplete value data";
        }

        // check input data
        if(description == null || category == null || description.isEmpty() || category.isEmpty())
        {
            // set error type
            hasError = true;
            errorString = "Required description and category!";
        }
        else if(!hasError)
        {
            // update cash flow in database
            cashFlow = new CashFlow(description, category, value, user.getUserID(), isProfit);
            cashFlow.setID(ID);
            try
            {
                DatabaseQueryManager.modifyCashFlow(connection, cashFlow);
            }
            catch(SQLException e)
            {
                // set error type
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }

        // update balance value in stored logined user if no error occured
        if(!hasError)
        {
            try
            {
                UserAccount updatedUser = DatabaseQueryManager.findUser(connection, user.getUserName());
                user.setBalance(updatedUser.getBalance());
            }
            catch (SQLException e)
            {
                // set error type
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }

        // get list of cash flows to store in request attribute
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
        }

        // store error string and lists of cashflow in request attribute
        request.setAttribute("errorString", errorString);
        request.setAttribute("profitsList", profitsList);
        request.setAttribute("spendingList", spendingList);

        // forward to cash flow view
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/cashFlowView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
