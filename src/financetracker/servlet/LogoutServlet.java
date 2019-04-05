package financetracker.servlet;

import financetracker.beans.Statistics;
import financetracker.beans.UserAccount;
import financetracker.manager.DatabaseQueryManager;
import financetracker.manager.TemporaryStoringManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get stored logined user from session
        HttpSession session = request.getSession();
        UserAccount user = TemporaryStoringManager.getStoredLoginedUser(session);

        //  store statistics about logout in database
        Connection connection = TemporaryStoringManager.getStoredConnection(request);
        String actionLog = "logout";
        Statistics statistics = new Statistics(user.getUserID(), actionLog);
        try
        {
            DatabaseQueryManager.insertStatistics(connection, statistics);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        // end Session with that user
        session.invalidate();

        // redirect to home page
        response.sendRedirect(request.getContextPath() + "/home");
    }
}
