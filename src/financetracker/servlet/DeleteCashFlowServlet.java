package financetracker.servlet;

import financetracker.beans.UserAccount;
import financetracker.manager.DatabaseQueryManager;
import financetracker.manager.TemporaryStoringManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "DeleteCashFlowServlet", urlPatterns = {"/deleteCashFlow"})
public class DeleteCashFlowServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get ID of cash flow and info if it is profit or spending
        String isProfitString = request.getParameter("isProfit");
        String cashFlowIDString = request.getParameter("cashFlowID");

        boolean isProfit = false;
        int cashFlowID = 0;

        String errorString = null;
        boolean hasError = false;

        Connection connection = TemporaryStoringManager.getStoredConnection(request);

        // check input data
        if(isProfitString == null || cashFlowIDString == null || isProfitString.trim().isEmpty() || cashFlowIDString.trim().isEmpty())
        {
            // set error type
            hasError = true;
            errorString = "Empty input from request";
        }
        else
        {
            // get data in right format
            try
            {
                isProfit = Boolean.parseBoolean(isProfitString);
                cashFlowID = Integer.parseInt(cashFlowIDString);
            }
            catch (Exception e)
            {
                hasError = true;
                errorString = "Can not parse data from request!";
            }
        }

        // delete cash flow from database - if no error occured
        if(!hasError)
        {
            try
            {
                DatabaseQueryManager.deleteCashFlow(connection, cashFlowID, isProfit);
            }
            catch(SQLException e)
            {
                // set error type
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }

        // update user balance value in stored logined user if no error occured
        if(!hasError)
        {
            try
            {
                UserAccount user = TemporaryStoringManager.getStoredLoginedUser(request.getSession());
                UserAccount updatedUser = DatabaseQueryManager.findUser(connection, user.getUserName());
                user.setBalance(updatedUser.getBalance());
            }
            catch (SQLException e)
            {
                // set error type
                e.printStackTrace();
            }
        }

        // set response
        response.setContentType("text/plain");
        if(!hasError)
        {
            response.getWriter().write("success");
        }
        else
        {
            response.getWriter().write(errorString);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
