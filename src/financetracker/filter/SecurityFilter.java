package financetracker.filter;

import financetracker.beans.UserAccount;
import financetracker.manager.TemporaryStoringManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/*"})
public class SecurityFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();
        // check if servlet path is home page - accessed for all users
        if(servletPath.equals("/") || servletPath.equals("/home"))
        {
            // go forward without any checking
            chain.doFilter(request, response);
            return;
        }


        // check if user is logined
        HttpSession session = request.getSession();
        UserAccount user = TemporaryStoringManager.getStoredLoginedUser(session);
        if(user == null)
        {
            // check if this servlet path requires log in
            if(servletPath.equals("/login") || servletPath.equals("/signup"))
            {
                // go forward without logging in
                chain.doFilter(request, response);
                return;
            }

            // user is not logined - redirect to login page
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // go forward - user is logined - has access to all pages - except from login and signup
        if(servletPath.equals("/login") || servletPath.equals("/signup"))
        {
            // user is already logined - redirect to home
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
