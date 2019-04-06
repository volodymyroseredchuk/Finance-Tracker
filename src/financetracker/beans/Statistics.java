package financetracker.beans;

import java.sql.Date;
import java.io.Serializable;

public class Statistics implements Serializable{

    private Date timeLog; // auto-set in database
    private String userName;
    private String actionLog; // may be "login", "logout", "signup" or "delete"

    public Statistics() {}

    public Statistics(String userName, String actionLog)
    {
        this.userName = userName;
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

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
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
