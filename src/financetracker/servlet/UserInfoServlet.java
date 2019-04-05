package financetracker.servlet;

import financetracker.beans.UserAccount;
import financetracker.manager.TemporaryStoringManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UserInfoServlet", urlPatterns = {"/userInfo"})
public class UserInfoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    // show user info page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get logined user
        HttpSession session = request.getSession();
        UserAccount user = TemporaryStoringManager.getStoredLoginedUser(session);

        // store user in request attribute
        request.setAttribute("user", user);

        // forward to userInfoView.jsp - all views is only accessed by servlets
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoView.jsp");
        dispatcher.forward(request, response);
    }
}
