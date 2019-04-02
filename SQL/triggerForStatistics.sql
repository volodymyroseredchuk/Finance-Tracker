-- Trigger creation for database FinanceTracker

IF OBJECT_ID (N'UserSignUpTrigger', N'TR') IS NOT NULL  
    DROP TRIGGER UserSignUpTrigger;  
GO

CREATE TRIGGER UserSignUpTrigger
ON [User_Account]
FOR INSERT
AS
DECLARE @userID int

SELECT @userID = ID FROM inserted

INSERT INTO [Statistics] (ActionLog, UserID)
VALUES (N'signup', @userID)