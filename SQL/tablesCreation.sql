-- Tables creation for database FinanceTracker

CREATE TABLE User_Account (
ID int IDENTITY(1,1) PRIMARY KEY,
UserName nvarchar(50) NOT NULL UNIQUE,
Password nvarchar(50) NOT NULL,
Role nvarchar(15) DEFAULT N'user', -- values: user, superuser, admin
Balance float DEFAULT 0.0
);

CREATE TABLE [Statistics] (
TimeLog datetime DEFAULT GETDATE(),
ActionLog nvarchar(15), -- values: signup, login, logout
UserID int FOREIGN KEY REFERENCES User_Account(ID)
);

CREATE TABLE Profits (
ID int IDENTITY(1,1) PRIMARY KEY,
Description nvarchar(255),
Category nvarchar(20),
CreationDate datetime DEFAULT GETDATE(),
Value float NOT NULL CHECK (Value > 0.0),
UserID int FOREIGN KEY REFERENCES User_Account(ID)
);

CREATE TABLE Spending (
ID int IDENTITY(1,1) PRIMARY KEY,
Description nvarchar(255),
Category nvarchar(20),
CreationDate datetime DEFAULT GETDATE(),
Value float NOT NULL CHECK (Value > 0.0),
UserID int FOREIGN KEY REFERENCES User_Account(ID)
);