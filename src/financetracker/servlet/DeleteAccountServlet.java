package financetracker.servlet;

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

@WebServlet(name = "DeleteAccountServlet", urlPatterns = {"/deleteAccount"})
public class DeleteAccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get input data
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        UserAccount user = null;
        String errorString = null;

        // check input data
        if(userName == null || password == null || userName.isEmpty() || password.isEmpty())
        {
            // set error type
            errorString = "Please input password to confirm deleting";
        }
        else
        {
            // find user in database
            Connection connection = TemporaryStoringManager.getStoredConnection(request);
            try
            {
                user = DatabaseQueryManager.findUser(connection, userName);
                // check if password is correct
                if(user == null || !password.equals(user.getPassword()))
                {
                    // set error type
                    errorString = "Confirmation password is invalid!";
                }
                else
                {
                    // delete account
                    DatabaseQueryManager.deleteUser(connection, userName);
                    // invalidate session and redirect to home page
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/home");
                    return;
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }

        if(user == null)
        {
            user = TemporaryStoringManager.getStoredLoginedUser(request.getSession());
        }
        // store user and error string in request attributes
        request.setAttribute("user", user);
        request.setAttribute("errorString", errorString);

        // forward to user info page
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
