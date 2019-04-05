package financetracker.manager;

import financetracker.beans.UserAccount;

import java.sql.Connection;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;


public class TemporaryStoringManager {
    private static final String ATTRIBUTE_CONNECTION = "ATTRIBUTE_CONNECTION";
    private static final String ATTRIBUTE_LOGINED_USER = "loginedUser";

    // store JDBC Connection in request attribute
    public static void storeConnection(ServletRequest request, Connection connection)
    {
        request.setAttribute(ATTRIBUTE_CONNECTION, connection);
    }

    // get stored JDBC Connection from request attribute
    public static Connection getStoredConnection(ServletRequest request)
    {
        Connection connection = (Connection) request.getAttribute(ATTRIBUTE_CONNECTION);
        return connection;
    }

    // store logined user in Session
    public static void storeLoginedUser(HttpSession session, UserAccount loginedUser)
    {
        session.setAttribute(ATTRIBUTE_LOGINED_USER, loginedUser);
    }

    // get stored logined user from Session
    public static UserAccount getStoredLoginedUser(HttpSession session)
    {
        UserAccount loginedUser = (UserAccount) session.getAttribute(ATTRIBUTE_LOGINED_USER);
        return loginedUser;
    }
}
