package financetracker.beans;

import java.io.Serializable;

public class UserAccount implements Serializable{

    private int userID; // auto-increment field in database
    private String userName; // should be unique for all users
    private String password;
    private String role = "user"; // may be "user", "superuser" or "admin"
    private double balance; // auto-calculate field in database

    public UserAccount() {}

    public UserAccount(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }

    public UserAccount(int userID, String userName, String password, String role, double balance)
    {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.balance = balance;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getRole()
    {
        return this.role;
    }

    public int getUserID()
    {
        return userID;
    }

    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }
}
