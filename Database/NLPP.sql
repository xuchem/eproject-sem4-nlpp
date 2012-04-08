use master
go
drop database NLPP
go

create database NLPP
go
use NLPP
go
create table Category
(
	CatID int identity(1,1) primary key,
	CatName varchar(50) not null,
	Visiable int default(0) not null
)
go
create table Events
(
	EventID int identity(1,1) primary key,
	CatID int references Category(CatID),
	EventName varchar(256) not null,
	DateCreate datetime not null,
	Address varchar(400) null,
	TimeEvent datetime not null,
	Deadline datetime not null,
	images varchar(256) not null,
	Description varchar(1000) not null,
	Details text not null,
	Price float not null,
	CountView int default(0) not null,
	Visiable int default(0) not null
)
go
create table Person
(
	PersonID int identity(1,1) primary key,
	PersonName varchar(50) not null,
	Email varchar(50) not null,
	Password varchar(50) not null,
	Phone varchar(20),
	Gender varchar(15) not null,
	Images varchar(256) null,
	Birthday datetime not null,
	Address varchar(256) not null,
	DateCreate datetime not null,
	Roles int default(0) not null,
	Visiable int default(0) not null
)
go
create table EnrollEvent
(
	EnrollEventID int identity(1,1) primary key,
	EventID int references Events(EventID),
	PersonID int references Person(PersonID),
	IsPayment int default(0) not null,
	DateReg datetime null,
	DatePay datetime null,
	PayMoney money null,
	IsWin int default(0) not null,
	Visiable int default(0) not null
)
go
create table Comment
(
	CommentID int identity(1,1) primary key,
	PersonID int references Person(PersonID),
	EventID int references Events(EventID),
	DateComment datetime not null,
	Contents varchar(max) not null,
	Visiable int default(0) not null
)
go
create table EmailRecieverEventCategory
(
	ERECID int identity(1,1) not null primary key,
	PersonID int references Person(PersonID),
	CatID int references Category(CatID)
)
go
INSERT INTO [NLPP].[dbo].[Person]
           ([PersonName]
           ,[Email]
           ,[Password]
           ,[Phone]
           ,[Gender]
           ,[Birthday]
           ,[Address]
           ,[DateCreate]
           ,[Images]
           ,[Roles]
           ,[Visiable])
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
go
INSERT INTO [NLPP].[dbo].[Person]
           ([PersonName]
           ,[Email]
           ,[Password]
           ,[Phone]
           ,[Gender]
           ,[Birthday]
           ,[Address]
           ,[DateCreate]
           ,[Images]
           ,[Roles]
           ,[Visiable])
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
go
INSERT INTO [NLPP].[dbo].[Person]
           ([PersonName]
           ,[Email]
           ,[Password]
           ,[Phone]
           ,[Gender]
           ,[Birthday]
           ,[Address]
           ,[DateCreate]
           ,[Images]
           ,[Roles]
           ,[Visiable])
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
go
INSERT INTO [NLPP].[dbo].[Person]
           ([PersonName]
           ,[Email]
           ,[Password]
           ,[Phone]
           ,[Gender]
           ,[Birthday]
           ,[Address]
           ,[DateCreate]
           ,[Images]
           ,[Roles]
           ,[Visiable])
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
go
INSERT INTO [NLPP].[dbo].[Person]
           ([PersonName]
           ,[Email]
           ,[Password]
           ,[Phone]
           ,[Gender]
           ,[Birthday]
           ,[Address]
           ,[DateCreate]
           ,[Images]
           ,[Roles]
           ,[Visiable])
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