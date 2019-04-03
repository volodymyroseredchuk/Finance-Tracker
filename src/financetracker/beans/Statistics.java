package financetracker.beans;

import net.sourceforge.jtds.jdbc.DateTime;

import java.io.Serializable;

public class Statistics implements Serializable{

    private DateTime timeLog; // auto-set in database
    private int userID;
    private String actionLog; // may be "login", "logout" or "signup"

    public Statistics() {}

    public Statistics(int userID, String actionLog)
    {
        this.userID = userID;
        this.actionLog = actionLog;
    }

    public void setTimeLog(DateTime timeLog)
    {
        this.timeLog = timeLog;
    }

    public DateTime getTimeLog()
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
