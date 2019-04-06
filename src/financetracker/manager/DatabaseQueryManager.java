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
    public static void insertUser(Connection connection, UserAccount user)
            throws SQLException
    {
        String sql = "INSERT INTO User_Account(UserName, Password, Role) " +
                "VALUES(?, ?, ?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getRole());

        preparedStatement.executeUpdate();
    }

    // find user by name in database
    public static UserAccount findUser(Connection connection, String userName)
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

    // delete user from database
    public static void deleteUser(Connection connection, String userName)
            throws SQLException
    {
        String sql = "DELETE FROM User_Account WHERE UserName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userName);

        preparedStatement.executeUpdate();
    }

    // modify user in database
    // ID, UserName should be the same
    // only password or role can be modified
    public static void modifyUser(Connection connection, UserAccount user)
            throws SQLException
    {
        String sql = "UPDATE User_Account SET Password = ?, Role = ? " +
                "WHERE ID = ? AND UserName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getPassword());
        preparedStatement.setString(2, user.getRole());
        preparedStatement.setInt(3, user.getUserID());
        preparedStatement.setString(4, user.getUserName());

        preparedStatement.executeUpdate();
    }

    // insert statistics info in database - login or logout or delete
    public static void insertStatistics(Connection connection, Statistics statistics)
        throws SQLException
    {
        String sql = "INSERT INTO [Statistics] (ActionLog, UserName) " +
                "VALUES(?, ?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, statistics.getActionLog());
        preparedStatement.setString(2, statistics.getUserName());

        preparedStatement.executeUpdate();
    }

    // get all statistics from database
    public static List<Statistics> queryStatistics(Connection connection)
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
            String userName = resultSet.getString("UserName");

            Statistics statistics = new Statistics(userName, actionLog);
            statistics.setTimeLog(timeLog);

            list.add(statistics);
        }
        return list;
    }


    // insert new cash flow in database
    public static void insertCashFlow(Connection connection, CashFlow cashFlow)
            throws SQLException
    {
        String tableName;
        if(cashFlow.getIsProfit())
        {
            // insert into Profits
            tableName = "Profits";
        }
        else
        {
            // insert into Spending
            tableName = "Spending";
        }

        String sql = "INSERT INTO ? (Description, Category, Value, UserID) " +
                "VALUES (?, ?, ?, ?) ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, tableName);
        preparedStatement.setString(2, cashFlow.getDescription());
        preparedStatement.setString(3, cashFlow.getCategory());
        preparedStatement.setDouble(4, cashFlow.getValue());
        preparedStatement.setInt(5, cashFlow.getUserID());

        preparedStatement.executeUpdate();
    }

    // delete cash flow from database
    public static void deleteCashFlow(Connection connection, int cashFlowID, boolean isProfit)
            throws SQLException
    {
        String tableName;
        if(isProfit)
        {
            // delete from Profits
            tableName = "Profits";
        }
        else
        {
            // delete from Spending
            tableName = "Spending";
        }

        String sql = "DELETE FROM ? WHERE ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, tableName);
        preparedStatement.setInt(2, cashFlowID);

        preparedStatement.executeUpdate();
    }

    // get all profits or spending for user - choice by boolean parameter
    public static List<CashFlow> queryCashFlow(Connection connection, int userID, boolean isProfit)
            throws SQLException
    {
        String tableName;
        if(isProfit)
        {
            // select from Profits
            tableName = "Profits";
        }
        else
        {
            // select from Spending
            tableName = "Spending";
        }

        List<CashFlow> list = new ArrayList<CashFlow>();
        String sql = "SELECT * FROM ? WHERE UserID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, tableName);
        preparedStatement.setInt(2, userID);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next())
        {
            int id = resultSet.getInt("ID");
            String description = resultSet.getString("Description");
            String category = resultSet.getString("Category");
            Date creationDate = resultSet.getDate("CreationDate");
            double value = resultSet.getDouble("Value");

            CashFlow cashFlow = new CashFlow(description, category, value, userID, isProfit);
            cashFlow.setCreationDate(creationDate);
            cashFlow.setID(id);

            list.add(cashFlow);
        }
        return list;
    }

    // modify cash flow in database
    // ID and UserID from old cash flow
    // only description, category or value can be modified
    public static void modifyCashFlow(Connection connection, CashFlow cashFlow)
            throws SQLException
    {
        String tableName;
        if(cashFlow.getIsProfit())
        {
            // update in Profits
            tableName = "Profits";
        }
        else
        {
            // update in Spending
            tableName = "Spending";
        }
        String sql = "UPDATE ? SET Description = ?, Category = ?, Value = ? " +
                "WHERE ID = ? AND UserID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, tableName);
        preparedStatement.setString(2, cashFlow.getDescription());
        preparedStatement.setString(3, cashFlow.getCategory());
        preparedStatement.setDouble(4, cashFlow.getValue());
        preparedStatement.setInt(5, cashFlow.getID());
        preparedStatement.setInt(6, cashFlow.getUserID());

        preparedStatement.executeUpdate();
    }
}
