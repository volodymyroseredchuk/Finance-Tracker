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

@WebServlet(name = "SignupServlet",urlPatterns = {"/signup"})
public class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get inputted data from form
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        UserAccount newUser = new UserAccount(userName, password);
        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;

        // check input data
        if(userName == null || password == null || userName.isEmpty() || password.isEmpty())
        {
            // set error type
            hasError = true;
            errorString = "Required username and password";
        }
        else
        {
            // check if user with that userName is already existed in database
            Connection connection = TemporaryStoringManager.getStoredConnection(request);
            try
            {
                // find user in database
                user = DatabaseQueryManager.findUser(connection, userName);
                if(user != null)
                {
                    // set error type
                    hasError = true;
                    errorString = "This username is already used!";
                }
                else
                {
                    // store new user in database
                    DatabaseQueryManager.insertUser(connection, newUser);
                }
            }
            catch(SQLException e)
            {
                // set error type
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }

        // if error - forward to signup page
        if(hasError)
        {
            // store user and error message in request attribute
            request.setAttribute("user", newUser);
            request.setAttribute("errorString", errorString);

            // forward to signupView.jsp
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/signupView.jsp");
            dispatcher.forward(request, response);
        }
        // if no error - redirect to login page
        else
        {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    // show sign up page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // forward to signupView.jsp - access to all view pages only by servlets
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/signupView.jsp");
        dispatcher.forward(request, response);
    }
}
