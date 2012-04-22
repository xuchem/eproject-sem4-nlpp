USE MASTER
GO
DROP DATABASE NLPP
GO

CREATE DATABASE NLPP
GO
USE NLPP
GO
CREATE TABLE [Categories]
(
	[CatID]				INT IDENTITY(1,1) PRIMARY KEY,
	[CatName]			VARCHAR(50) NOT NULL,
	[Status]			INT DEFAULT(0) NOT NULL
)
GO
CREATE TABLE [Events]
(
	[EventID]			INT IDENTITY(1,1) PRIMARY KEY,
	[CatID]				INT REFERENCES [Categories]([CatID]),
	[EventName]			VARCHAR(256) NOT NULL,
	[CreatedDate]		DATETIME NOT NULL,
	[Address]			VARCHAR(400) null,
	[ConductingDate]	DATETIME NOT NULL,
	[EnrollmentDeadline]DATETIME NOT NULL,
	[images]			VARCHAR(256) NOT NULL,
	[Description]		VARCHAR(1000) NOT NULL,
	[Details]			TEXT NOT NULL,
	[Price]				FLOAT NOT NULL,
	[NumbOfView]		INT DEFAULT(0) NOT NULL,
	[Status]			INT DEFAULT(0) NOT NULL
)
GO
CREATE TABLE [Users]
(
	[UserID]			INT IDENTITY(1,1) PRIMARY KEY,
	[FullName]			VARCHAR(50) NOT NULL,
	[Email]				VARCHAR(50) NOT NULL,
	[Password]			VARCHAR(50) NOT NULL,
	[Phone]				VARCHAR(20),
	[Gender]			VARCHAR(15) NOT NULL,
	[Images]			VARCHAR(256) null,
	[Birthday]			DATETIME NOT NULL,
	[Address]			VARCHAR(256) NOT NULL,
	[CreatedDate]		DATETIME NOT NULL,
	[Roles]				INT DEFAULT(0) NOT NULL,
	[Status]			INT DEFAULT(0) NOT NULL
)
GO
CREATE TABLE [EnrollEvents]
(
	[EnrollEventID]		INT IDENTITY(1,1) PRIMARY KEY,
	[EventID]			INT REFERENCES [Events]([EventID]),
	[UserID]			INT REFERENCES [Users]([UserID]),
	[IsPaid]			INT DEFAULT(0) NOT NULL,
	[RegisterDate]		DATETIME,
	[PaidDate]			DATETIME,
	[PayMoney]			MONEY,
	[IsWin]				INT DEFAULT(0) NOT NULL,
	[Status]			INT DEFAULT(0) NOT NULL
)
GO
CREATE TABLE [Comments]
(
	[CommentID]			INT IDENTITY(1,1) PRIMARY KEY,
	[UserID]			INT REFERENCES [Users]([UserID]),
	[EventID]			INT REFERENCES [Events]([EventID]),
	[CreatedDate]		DATETIME NOT NULL,
	[Contents]			VARCHAR(MAX) NOT NULL,
	[Status]			INT DEFAULT(0) NOT NULL
)
GO
CREATE TABLE [EventNotifications]
(
	[EvenNotificationID]INT IDENTITY(1,1) PRIMARY KEY,
	[UserID]			INT REFERENCES [Users]([UserID]),
	[CatID]				INT REFERENCES [Categories]([CatID])
)
GO
INSERT INTO [NLPP].[dbo].[Users]
           ([FullName]
           ,[Email]
           ,[Password]
           ,[Phone]
           ,[Gender]
           ,[Birthday]
           ,[Address]
           ,[CreatedDate]
           ,[Images]
           ,[Roles]
           ,[Status])
     VALUES
           ('Administrator'
           ,'admin@ksc.com'
           ,'e10adc3949ba59abbe56e057f20f883e'
           ,'0987654321'
           ,'Male'
           ,'01/01/01'
           ,'HN'
           ,'01/01/11'
           ,NULL
           ,1
           ,0)
GO
INSERT INTO [NLPP].[dbo].[Users]
           ([FullName]
           ,[Email]
           ,[Password]
           ,[Phone]
           ,[Gender]
           ,[Birthday]
           ,[Address]
           ,[CreatedDate]
           ,[Images]
           ,[Roles]
           ,[Status])
     VALUES
           ('User1'
           ,'user1@ksc.com'
           ,'e10adc3949ba59abbe56e057f20f883e'
           ,'0987654321'
           ,'Male'
           ,'01/01/01'
           ,'HN'
           ,'01/01/11'
           ,NULL
           ,0
           ,0)
GO
INSERT INTO [NLPP].[dbo].[Users]
           ([FullName]
           ,[Email]
           ,[Password]
           ,[Phone]
           ,[Gender]
           ,[Birthday]
           ,[Address]
           ,[CreatedDate]
           ,[Images]
           ,[Roles]
           ,[Status])
     VALUES
           ('Accountant'
           ,'accountant@ksc.com'
           ,'e10adc3949ba59abbe56e057f20f883e'
           ,'0987654321'
           ,'Male'
           ,'01/01/01'
           ,'HN'
           ,'01/01/11'
           ,NULL
           ,2
           ,0)
GO
INSERT INTO [NLPP].[dbo].[Users]
           ([FullName]
           ,[Email]
           ,[Password]
           ,[Phone]
           ,[Gender]
           ,[Birthday]
           ,[Address]
           ,[CreatedDate]
           ,[Images]
           ,[Roles]
           ,[Status])
     VALUES
           ('User2'
           ,'user2@ksc.com'
           ,'e10adc3949ba59abbe56e057f20f883e'
           ,'0987654321'
           ,'Male'
           ,'01/01/01'
           ,'HN'
           ,'01/01/11'
           ,NULL
           ,0
           ,0)
GO
INSERT INTO [NLPP].[dbo].[Users]
           ([FullName]
           ,[Email]
           ,[Password]
           ,[Phone]
           ,[Gender]
           ,[Birthday]
           ,[Address]
           ,[CreatedDate]
           ,[Images]
           ,[Roles]
           ,[Status])
     VALUES
           ('Manager'
           ,'manager@ksc.com'
           ,'e10adc3949ba59abbe56e057f20f883e'
           ,'0987654321'
           ,'Male'
           ,'01/01/01'
           ,'HN'
           ,'01/01/11'
           ,NULL
           ,1
           ,0)
           
    