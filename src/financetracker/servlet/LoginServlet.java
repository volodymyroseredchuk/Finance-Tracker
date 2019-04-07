package financetracker.servlet;

import financetracker.beans.Statistics;
import financetracker.beans.UserAccount;
import financetracker.connection.DatabaseConnection;
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
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    // this method will be called on click 'Submit' on login form
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get login info from form
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;

        // check input data
        if(userName == null || password == null || userName.trim().isEmpty() || password.trim().isEmpty())
        {
            // set type of error
            hasError = true;
            errorString = "Required user name and password!";
        }
        else
        {
            // get rid of redundant whitespaces if they are present
            userName = userName.trim();
            password = password.trim();
            // check login credentials
            Connection connection = TemporaryStoringManager.getStoredConnection(request);
            try
            {
                // find user in database
                user = DatabaseQueryManager.findUser(connection, userName);

                if(user == null || !user.getPassword().equals(password))
                {
                    // user is not found or password is invalid
                    hasError = true;
                    errorString = "User name or password is invalid!";
                }
            }
            catch(SQLException e)
            {
                // set type of error
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }

        // if error - forward to login page
        if(hasError)
        {
            // store user and error message in request attribute
            user = new UserAccount(userName, password);
            request.setAttribute("user", user);
            request.setAttribute("errorString", errorString);

            // forward to loginView.jsp
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
            dispatcher.forward(request, response);
        }
        // if no error - forward to home page
        else
        {
            // store logined user in Session
            HttpSession session = request.getSession();
            TemporaryStoringManager.storeLoginedUser(session, user);

            //  store statistics about login in database
            Connection connection = TemporaryStoringManager.getStoredConnection(request);
            String actionlog = "login";
            Statistics statistics = new Statistics(user.getUserName(), actionlog);
            try
            {
                DatabaseQueryManager.insertStatistics(connection, statistics);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }


            String redirect = request.getParameter("redirect");
            if(redirect == null || redirect.isEmpty())
            {
                // redirect to home page
                response.sendRedirect(request.getContextPath() + "/home");
            }
            else
            {
                // redirect to page from which user came to login page
                response.sendRedirect(redirect);
            }
        }
    }

    // show login page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // forward to loginView.jsp - access to all views only by servlets
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
        dispatcher.forward(request, response);
    }
}
