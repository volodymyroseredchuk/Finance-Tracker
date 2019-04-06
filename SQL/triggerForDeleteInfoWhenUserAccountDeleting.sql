-- Trigger creation for database FinanceTracker

IF OBJECT_ID (N'UserDeletedTrigger', N'TR') IS NOT NULL  
    DROP TRIGGER UserDeletedTrigger;  
GO

CREATE TRIGGER UserDeletedTrigger
ON [User_Account]
FOR DELETE
AS
DECLARE @userID int
DECLARE @userName nvarchar(50)
SELECT @userID = ID, @userName = UserName FROM deleted

DELETE FROM Profits WHERE UserID = @userID
DELETE FROM Spending WHERE UserID = @userID

INSERT INTO [Statistics] (ActionLog, UserName)
VALUES (N'delete', @userName)