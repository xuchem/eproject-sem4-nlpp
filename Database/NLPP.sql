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

GO     
INSERT INTO [NLPP].[dbo].[Categories] VALUES ('University',1)
INSERT INTO [NLPP].[dbo].[Categories] VALUES ('School',1)
INSERT INTO [NLPP].[dbo].[Categories] VALUES ('Faculty',1)

GO
INSERT INTO [NLPP].[dbo].[Events] VALUES (1,'Why Evolution Is True and Why Many People Still Don''t Believe It','May 2, 2012','Museum of Natural History','May 10, 2012','May 7, 2012','1.jpg','Jerry Coyne, professor of ecology and evolution, University of Chicago and author of "Why Evolution is True"','Jerry Coyne is one of the world’s most eloquent defenders of evolutionary science in the face of legal, religious, and cultural opposition. In this talk, Coyne will explore the multifarious evidence for evolution, why Americans are so resistant to accepting the theory, and what can be done to make the country more evolution-friendly. Free and open to the public. Parking available in the 52 Oxford Street garage. Part of the Evolution Matters lecture series. Supported by a generous gift from Herman and Joan Suit.',10,300,1)

INSERT INTO [NLPP].[dbo].[Events] VALUES (3,'The Jacob Pat Memorial Lecture. "Can a Woman Poet Write in Yiddish After the Holocaust?','Apr 23, 2012','Lamont Library Forum Room','Apr 30, 2012','Apr 28, 2012','2.jpg','Kathryn Hellerstein, associate professor of Germanic languages and literatures (Yiddish), University of Pennsylvania; visiting research fellow, Center for Jewish Studies, Harvard University','A project of The Jacob and Frieda Pat Endowment.',7,100,1)

INSERT INTO [NLPP].[dbo].[Events] VALUES (2,'Syrup, Seeds, and Bees: Exploring Links in Maple Ecology','April 23, 2012','Weld Hill Research Building','May 5, 2012','May 1, 2012','3.jpg','Elizabeth Crone, Population Biologist','Elizabeth Crone’s research focuses on the population ecology, life history, and conservation of plants and insects. Most recently, she has turned her attention to a signature “industry” of New England—maple syrup production. She is trying to determine any links between pollinator populations, quantity of maple flower and seeds, and sap flow. Join us to learn if each responds independently to the weather or if there are possibly complex, but subtle, interactions taking place in the sugar bush. Free, but registration requested Offered with the Cambridge Science Festival and the New England Wild Flower Society',2,500,1)

INSERT INTO [NLPP].[dbo].[Events] VALUES (2,'John Harvard Book Celebration','April 1, 2012','Campus A','Apr 18, 2012','Apr 20, 2012','4.png','For more information, please email community@harvard.edu or call 617-495-4955','As part of Harvard’s 375th anniversary and in celebration of its close ties to the Boston and Cambridge communities, the University has developed a lecture series that will bring some of Harvard’s most renowned thinkers to speak at neighborhood libraries. Developed in partnership with the Boston and Cambridge public libraries, the John Harvard Book Celebration program will reach all 34 library branches in Boston and Cambridge.  All events will be free and open to the public. Harvard faculty members will discuss topics ranging from why violence has declined, to theater in the 21st Century at branch libraries in the two cities. Speakers will include some of Harvard’s most prominent faculty, including Steven Pinker, one of the world’s leading authorities on language and the mind; Diane Paulus, critically acclaimed artistic director of the American Repertory Theater and a Tony Award winner; and Maria Tatar, a German cultural studies scholar who unlocks the secrets of children’s literature and folklore. See below for the schedule of upcoming lectures. Seating is limited at all lectures. Seating is available on a first-come, first serve basis. For more information, please email community@harvard.edu or call 617-495-4955. In honor of John Harvard’s gift of 400 books given to the University in 1638, the University will donate 400 new books to the two cities’ library systems.  Harvard staff, faculty, and students also will take part in library activities aimed at children and youths, including story hours, college readiness discussions, and other events.',5,240,1)

INSERT INTO [NLPP].[dbo].[Events] VALUES (2,'Economics 2690hf: Environmental Economics and Policy Seminar','April 10, 2012','KSG - Littauer 382','Apr 20, 2012','Apr 18, 2012','5.jpg','Robert N. Stavins (Kennedy School) and Martin L. Weitzman','Selected topics in environmental and resource economics. Emphasizes theoretical models, quantitative empirical analysis, and public policy applications. Includes invited outside speakers. Note: Primarily for graduate students in economics or related fields with environmental interests. Offered jointly with the Kennedy School as API-905Y. Prerequisite: Graduate-level course in microeconomic theory.',12,80,1)

INSERT INTO [NLPP].[dbo].[Events] VALUES (1,'Who is Responsible for the Culture of Medicine?','April 10, 2012','Radcliffe Gymnasium','Apr 24, 2012','Apr 20, 2012','6.gif','David S. Jones, MD, PhD','Medicine has become a defining feature of modern society. It simultaneously shapes, and is shaped by, our cultural values and practices. To understand medicine, it is necessary to broaden the inquiry beyond the biomedical sciences to include the humanities, arts and social sciences. The Ackerman Program will foster broad-ranging collaborative scholarship and activities that explore the many cultures of medicine.',10,200,1)