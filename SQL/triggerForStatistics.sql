-- Trigger creation for database FinanceTracker

IF OBJECT_ID (N'UserSignUpTrigger', N'TR') IS NOT NULL  
    DROP TRIGGER UserSignUpTrigger;  
GO

CREATE TRIGGER UserSignUpTrigger
ON [User_Account]
FOR INSERT
AS
DECLARE @userName nvarchar(50)

SELECT @userName = UserName FROM inserted

INSERT INTO [Statistics] (ActionLog, UserName)
VALUES (N'signup', @userName)