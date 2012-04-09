GO
USE MASTER
GO
IF EXISTS (SELECT * FROM SYSDATABASES WHERE name='NLPP')
DROP DATABASE NLPP
GO
CREATE DATABASE NLPP
GO
USE NLPP
GO

CREATE TABLE [Users]
(
	[Username]				VARCHAR(30) PRIMARY KEY,
	[Password]				VARCHAR(30) NOT NULL,
	[IsAdmin]				BIT NOT NULL,
	[FullName]				NVARCHAR(100) NOT NULL,
	[Gender]				BIT NOT NULL,
	[DateOfBirth]			DATE NOT NULL,
	[Address]				NVARCHAR(200),
	[Phone]					VARCHAR(20),
	[Email]					VARCHAR(30) NOT NULL,
	[JoinDate]				DATE NOT NULL DEFAULT GETDATE(),
)

CREATE TABLE [EventTypes]
(
	[EventTypeID]			INT IDENTITY(1,1) PRIMARY KEY,
	[EventTypeName]			NVARCHAR(30) NOT NULL UNIQUE,
	[Description]			NVARCHAR(30),
)

CREATE TABLE [Events]
(
	[EventID]				INT IDENTITY(1,1) PRIMARY KEY,
	[EventName]				VARCHAR(100) NOT NULL,
	[EventType]				INT REFERENCES [EventTypes]([EventTypeID]) NOT NULL,
	[Topic]					NVARCHAR(1000) NOT NULL,
	[Speakers]				NVARCHAR(1000),
	[Guests]				NVARCHAR(1000),
	[Criteria]				NVARCHAR(1000),
	[TermsAndConditions]	NVARCHAR(1000),
	[Location]				NVARCHAR(1000) NOT NULL,
	[ConductingDate]		DATE NOT NULL,
	[Fees]					FLOAT,
	[NumberOfParticipants]	INT NOT NULL DEFAULT 0,
)

CREATE TABLE [EventPrizes]
(
	[EventPrizeID]			INT IDENTITY(1,1) PRIMARY KEY,
	[EventID]				INT REFERENCES [Events]([EventID]) NOT NULL,
	[PrizeDetail]			NVARCHAR(1000),
	[Winners]				NVARCHAR(100),
)

CREATE TABLE [PaymentType]
(
	[PaymentTypeID]			INT IDENTITY(1,1) PRIMARY KEY,
	[PaymentTypeName]		VARCHAR(100) NOT NULL,
)

CREATE TABLE [EventEnrollments]
(
	[EventID]				INT REFERENCES [Events]([EventID]) NOT NULL,
	[Username]				VARCHAR(30) REFERENCES [Users]([Username]) NOT NULL,
	PRIMARY KEY([EventID],[Username]),
	[PaymentType]			INT REFERENCES [PaymentType]([PaymentTypeID]) NOT NULL,
	[IsPaid]				BIT NOT NULL,
	[DateOfEnrollment]		DATE NOT NULL DEFAULT GETDATE(),
	[DateOfPayment]			DATE,
)

CREATE TABLE [EventNotifications]
(
	[Username]				VARCHAR(30) REFERENCES [Users]([Username]) NOT NULL,
	[EventType]				INT REFERENCES [EventTypes]([EventTypeID]) NOT NULL,
)