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

@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/changePassword"})
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get info from form
        String userName = request.getParameter("username");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        String errorString = null;
        UserAccount user = null;

        // check input data
        if(userName == null || oldPassword == null || newPassword == null || confirmPassword == null ||
        userName.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty())
        {
            // set error type
            errorString = "Please input all fields!";
        }
        else
        {
            // find user in database
            Connection connection = TemporaryStoringManager.getStoredConnection(request);
            try
            {
                user = DatabaseQueryManager.findUser(connection, userName);
                // check if old password is typed correctly
                if(user == null || !oldPassword.equals(user.getPassword()))
                {
                    // set error type
                    errorString = "Password is invalid!";
                }
                // check if new password and its confirmation is equal
                else if(!newPassword.equals(confirmPassword))
                {
                    // set error type
                    errorString = "Confirmation password is invalid!";
                }
                else
                {
                    // change password
                    user.setPassword(newPassword);
                    DatabaseQueryManager.modifyUser(connection, user);
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
        // store user and error string and success in request attribute
        request.setAttribute("changePasswordSuccess", "success");
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
