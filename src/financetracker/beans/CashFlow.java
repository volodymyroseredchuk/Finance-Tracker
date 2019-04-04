package financetracker.beans;

import java.io.Serializable;

import java.sql.Date;
/*
 class that simulates tables Profits and Spending from database
 all fields in that tables are the same, but this tables are divided for logic purposes

 but here we can implement one class for two tables, having flag that indicates
 whether it is profit or spending
 */
public class CashFlow implements Serializable{

    private boolean isProfit; // true for Profit, false for Spending

    private int ID; // auto-increment field in database
    private String description;
    private String category;
    private Date creationDate; // auto-set field in database
    private double value;
    private int userID;

    public CashFlow() {}

    public CashFlow(String description, String category, double value, int userID, boolean isProfit)
    {
        this.description = description;
        this.category = category;
        this.value = value;
        this.userID = userID;
        this.isProfit = isProfit;
    }

    public boolean getIsProfit()
    {
        return isProfit;
    }

    public void setIsProfit(boolean isProfit)
    {
        this.isProfit = isProfit;
    }

    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public double getValue()
    {
        return value;
    }

    public void setValue(double value)
    {
        this.value = value;
    }

    public int getUserID()
    {
        return userID;
    }

    public void setUserID(int userID)
    {
        this.userID = userID;
    }
}
