package financetracker.manager;

import financetracker.beans.Statistics;
import financetracker.beans.UserAccount;
import financetracker.beans.CashFlow;

import java.sql.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryManager {

    // insert user in database
    public void insertUser(Connection connection, UserAccount user)
            throws SQLException
    {
        String sql = "INSERT INTO User_Account(UserName, Password, Role) " +
                "VALUES(?,?,?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getRole());

        preparedStatement.executeUpdate();
    }

    // find user by name in database
    public UserAccount findUser(Connection connection, String userName)
            throws SQLException
    {
        String sql = "SELECT * FROM User_Account WHERE UserName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next())
        {
            int userID = resultSet.getInt("ID");
            String password = resultSet.getString("Password");
            String role = resultSet.getString("Role");
            double balance = resultSet.getDouble("Balance");

            UserAccount user = new UserAccount(userID, userName, password, role, balance);
            return user;
        }
        return null;
    }

    // find user by name and password in database
    public UserAccount findUser(Connection connection, String userName, String password)
            throws SQLException
    {
        String sql = "SELECT * FROM User_Account WHERE UserName = ? AND Password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next())
        {
            int userID = resultSet.getInt("ID");
            String role = resultSet.getString("Role");
            double balance = resultSet.getDouble("Balance");

            UserAccount user = new UserAccount(userID, userName, password, role, balance);
            return user;
        }
        return null;
    }

    // delete user from database
    public void deleteUser(Connection connection, String userName)
            throws SQLException
    {
        String sql = "DELETE FROM User_Account WHERE UserName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userName);

        preparedStatement.executeUpdate();
    }


    // insert statistic info in database - login or logout or delete
    public void insertStatistics(Connection connection, Statistics statistics)
        throws SQLException
    {
        String sql = "INSERT INTO Statistics(ActionLog, UserID) " +
                "VALUES(?,?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, statistics.getActionLog());
        preparedStatement.setInt(2, statistics.getUserID());

        preparedStatement.executeUpdate();
    }

    // get all statistic
    public List<Statistics> queryStatistics(Connection connection)
        throws SQLException
    {
        List<Statistics> list = new ArrayList<Statistics>();
        String sql = "SELECT * FROM Statistics";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next())
        {
            Date timeLog = resultSet.getDate("TimeLog");
            String actionLog = resultSet.getString("ActionLog");
            int userID = resultSet.getInt("UserID");

            Statistics statistics = new Statistics(userID, actionLog);
            statistics.setTimeLog(timeLog);

            list.add(statistics);
        }
        return list;
    }
}
