package financetracker.filter;

import financetracker.connection.DatabaseConnection;
import financetracker.manager.TemporaryStoringManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

/*
This filter will set database connection in request attribute -
if request opens for servlet - else go next without database connection
 */
@WebFilter(filterName = "JDBCFilter", urlPatterns = {"/*"})
public class JDBCFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // check if request to servlet
        if(isRequestForServlet(httpRequest))
        {
            // opens Connection with database
            Connection connection = null;
            try
            {
                connection = DatabaseConnection.getConnection();
                connection.setAutoCommit(false);// set commit in manual mode

                // store Connection in request attribute
                TemporaryStoringManager.storeConnection(request, connection);

                // go forward with Connection
                chain.doFilter(request, response);

                // complete transaction with database
                connection.commit();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                DatabaseConnection.rollbackConnection(connection);
                throw new ServletException();
            }
            finally
            {
                DatabaseConnection.closeConnection(connection);
            }
        }
        else
        {
            // go forward without Connection
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    private boolean isRequestForServlet(HttpServletRequest request)
    {
        String servletPath = request.getServletPath();
        String pathInfo = request.getPathInfo();
        String urlPattern = servletPath;
        // establish urlPattern for checking that it contains only Servlet path without any additional info
        if(pathInfo != null)
        {
            urlPattern = servletPath + "/*";
        }

        // get collection of all servlets in app
        Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext().getServletRegistrations();
        Collection<? extends ServletRegistration> servletRegistrationsValues = servletRegistrations.values();
        for(ServletRegistration servletRegistration : servletRegistrationsValues)
        {
            // check if any of servlets path matches with our urlPattern
            Collection<String> mappings = servletRegistration.getMappings();
            if(mappings.contains(urlPattern))
            {
                return true;
            }
        }
        return false;
    }
}
