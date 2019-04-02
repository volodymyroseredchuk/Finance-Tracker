-- Trigger creation for database FinanceTracker

IF OBJECT_ID (N'BalanceUpdateProfitsTrigger', N'TR') IS NOT NULL  
    DROP TRIGGER BalanceUpdateProfitsTrigger;  
GO

CREATE TRIGGER BalanceUpdateProfitsTrigger
ON [Profits]
AFTER UPDATE, INSERT, DELETE
AS

DECLARE @balance float
DECLARE @balanceChange float
DECLARE @userID int

IF EXISTS(SELECT * FROM inserted) AND EXISTS (SELECT * FROM deleted)
BEGIN
    -- UPDATE
	DECLARE @insertedBalance float
	DECLARE @deletedBalance float

	SELECT @userID = UserID, @insertedBalance = [Value] FROM inserted
	SELECT @deletedBalance = [Value] FROM deleted
	SELECT @balance = Balance FROM User_Account WHERE ID = @userID

	SET @balance = @balance + @insertedBalance - @deletedBalance
END

IF EXISTS(SELECT * FROM inserted) AND NOT EXISTS (SELECT * FROM deleted)
BEGIN
    -- INSERT
	SELECT @userID = UserID, @balanceChange = [Value] FROM inserted
	SELECT @balance = Balance FROM User_Account WHERE ID = @userID

	SET @balance = @balance + @balanceChange
END

IF EXISTS(SELECT * FROM deleted) AND NOT EXISTS (SELECT * FROM inserted)
BEGIN 
    -- DELETE
	SELECT @userID = UserID, @balanceChange = [Value] FROM deleted
	SELECT @balance = Balance FROM User_Account WHERE ID = @userID

	SET @balance = @balance - @balanceChange
END

UPDATE User_Account SET Balance = @balance WHERE ID = @userID