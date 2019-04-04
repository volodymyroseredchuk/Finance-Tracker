package financetracker.beans;

import java.sql.Date;
import java.io.Serializable;

public class Statistics implements Serializable{

    private Date timeLog; // auto-set in database
    private int userID;
    private String actionLog; // may be "login", "logout", "signup" or "delete"

    public Statistics() {}

    public Statistics(int userID, String actionLog)
    {
        this.userID = userID;
        this.actionLog = actionLog;
    }

    public void setTimeLog(Date timeLog)
    {
        this.timeLog = timeLog;
    }

    public Date getTimeLog()
    {
        return timeLog;
    }

    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    public int getUserID()
    {
        return userID;
    }

    public void setActionLog(String actionLog)
    {
        this.actionLog = actionLog;
    }

    public String getActionLog()
    {
        return actionLog;
    }
}
