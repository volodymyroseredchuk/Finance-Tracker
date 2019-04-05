-- Trigger creation for database FinanceTracker

IF OBJECT_ID (N'UserDeletedTrigger', N'TR') IS NOT NULL  
    DROP TRIGGER UserDeletedTrigger;  
GO

CREATE TRIGGER UserDeletedTrigger
ON [User_Account]
FOR DELETE
AS
DECLARE @userID int
SELECT @userID = ID FROM deleted

DELETE FROM Profits WHERE UserID = @userID
DELETE FROM Spending WHERE UserID = @userID

INSERT INTO [Statistics] (ActionLog, UserID)
VALUES (N'delete', @userID)