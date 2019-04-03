package financetracker.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection getConnection()
            throws SQLException, ClassNotFoundException
    {
        // load Driver class from JTDS to connect to database with JDBC
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        // set credentials for your database
        String hostName = "localhost";
        String sqlInstanceName = "MSSQLSERVER";
        String database = "FinanceTracker";
        String userName = "";
        String password = "";

        String connectionURL = "jdbc:jtds:sqlserver://" + hostName + ":1433/" +
                database + ";instance=" + sqlInstanceName;

        Connection connection = DriverManager.getConnection(connectionURL, userName, password);
        return connection;
    }

    public static void closeConnection(Connection connection)
    {
        try
        {
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void rollbackConnection(Connection connection)
    {
        try
        {
            connection.rollback();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
