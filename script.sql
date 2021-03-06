USE [HanaShop]
GO
ALTER TABLE [dbo].[Updation] DROP CONSTRAINT [FK_Updation_Registration]
GO
ALTER TABLE [dbo].[Orders] DROP CONSTRAINT [FK_Orders_Registration]
GO
ALTER TABLE [dbo].[OrderDetail] DROP CONSTRAINT [FK_OrderDetail_Orders]
GO
ALTER TABLE [dbo].[OrderDetail] DROP CONSTRAINT [FK_OrderDetail_Foods]
GO
ALTER TABLE [dbo].[Foods] DROP CONSTRAINT [FK_Foods_Category]
GO
/****** Object:  Table [dbo].[Updation]    Script Date: 5/4/2021 7:26:50 PM ******/
DROP TABLE [dbo].[Updation]
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 5/4/2021 7:26:50 PM ******/
DROP TABLE [dbo].[Registration]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 5/4/2021 7:26:50 PM ******/
DROP TABLE [dbo].[Orders]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 5/4/2021 7:26:50 PM ******/
DROP TABLE [dbo].[OrderDetail]
GO
/****** Object:  Table [dbo].[Foods]    Script Date: 5/4/2021 7:26:50 PM ******/
DROP TABLE [dbo].[Foods]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 5/4/2021 7:26:50 PM ******/
DROP TABLE [dbo].[Category]
GO
USE [master]
GO
/****** Object:  Database [HanaShop]    Script Date: 5/4/2021 7:26:50 PM ******/
DROP DATABASE [HanaShop]
GO
/****** Object:  Database [HanaShop]    Script Date: 5/4/2021 7:26:50 PM ******/
CREATE DATABASE [HanaShop]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'HanaShop', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\HanaShop.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'HanaShop_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\HanaShop_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [HanaShop] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [HanaShop].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [HanaShop] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [HanaShop] SET ARITHABORT OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [HanaShop] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [HanaShop] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [HanaShop] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [HanaShop] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [HanaShop] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [HanaShop] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [HanaShop] SET  DISABLE_BROKER 
GO
ALTER DATABASE [HanaShop] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [HanaShop] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [HanaShop] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [HanaShop] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [HanaShop] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [HanaShop] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [HanaShop] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [HanaShop] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [HanaShop] SET  MULTI_USER 
GO
ALTER DATABASE [HanaShop] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [HanaShop] SET DB_CHAINING OFF 
GO
ALTER DATABASE [HanaShop] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [HanaShop] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [HanaShop] SET DELAYED_DURABILITY = DISABLED 
GO
USE [HanaShop]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 5/4/2021 7:26:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Category](
	[Category] [varchar](50) NOT NULL,
	[Description] [varchar](50) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[Category] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Foods]    Script Date: 5/4/2021 7:26:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Foods](
	[FoodID] [varchar](50) NOT NULL,
	[Name] [varchar](50) NULL,
	[Description] [varchar](500) NULL,
	[Image] [varchar](500) NULL,
	[Quantity] [int] NULL,
	[Price] [float] NULL,
	[Category] [varchar](50) NULL,
	[DateOfCreate] [datetime] NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_Foods] PRIMARY KEY CLUSTERED 
(
	[FoodID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 5/4/2021 7:26:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[OrderDetailID] [varchar](50) NOT NULL,
	[OrderID] [varchar](50) NULL,
	[Quantity] [int] NULL,
	[FoodID] [varchar](50) NULL,
	[Price] [float] NULL,
 CONSTRAINT [PK_OrderDe] PRIMARY KEY CLUSTERED 
(
	[OrderDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 5/4/2021 7:26:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Orders](
	[OrderID] [varchar](50) NOT NULL,
	[Username] [varchar](50) NULL,
	[Total] [float] NULL,
	[DateOfCreate] [datetime] NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 5/4/2021 7:26:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Registration](
	[Username] [varchar](50) NOT NULL,
	[Password] [varchar](50) NULL,
	[Fullname] [varchar](50) NULL,
	[Role] [varchar](50) NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_Registration] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Updation]    Script Date: 5/4/2021 7:26:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Updation](
	[UpdationID] [varchar](20) NOT NULL,
	[Username] [varchar](50) NULL,
	[FoodID] [varchar](50) NULL,
	[DateOfCreate] [datetime] NULL,
	[Action] [varchar](50) NULL,
 CONSTRAINT [PK_Updation] PRIMARY KEY CLUSTERED 
(
	[UpdationID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Category] ([Category], [Description]) VALUES (N'drink', N'asdasd')
INSERT [dbo].[Category] ([Category], [Description]) VALUES (N'food', N'food')
INSERT [dbo].[Category] ([Category], [Description]) VALUES (N'fruit', N'asdas')
INSERT [dbo].[Category] ([Category], [Description]) VALUES (N'meat', N'assdf')
INSERT [dbo].[Foods] ([FoodID], [Name], [Description], [Image], [Quantity], [Price], [Category], [DateOfCreate], [Status]) VALUES (N'F-1', N'apple', N'cccc', N'apple.jpg', 77, 23200, N'fruit', CAST(N'2020-11-11 00:00:00.000' AS DateTime), 1)
INSERT [dbo].[Foods] ([FoodID], [Name], [Description], [Image], [Quantity], [Price], [Category], [DateOfCreate], [Status]) VALUES (N'F-10', N'cherry', N'qweqwe', N'cherry.jpg', 12, 12500, N'fruit', CAST(N'2021-01-23 21:14:23.910' AS DateTime), 1)
INSERT [dbo].[Foods] ([FoodID], [Name], [Description], [Image], [Quantity], [Price], [Category], [DateOfCreate], [Status]) VALUES (N'F-11', N'girl', N'wewerwer', N'search.jpg', 12, 12000, N'drink', CAST(N'2021-01-23 21:20:55.763' AS DateTime), 1)
INSERT [dbo].[Foods] ([FoodID], [Name], [Description], [Image], [Quantity], [Price], [Category], [DateOfCreate], [Status]) VALUES (N'F-12', N'Cocacola', N'Coca nhaaaaa', N'coca.jpg', 38, 10000, N'drink', CAST(N'2021-01-24 13:56:55.777' AS DateTime), 1)
INSERT [dbo].[Foods] ([FoodID], [Name], [Description], [Image], [Quantity], [Price], [Category], [DateOfCreate], [Status]) VALUES (N'F-13', N'orange', N'fresh', N'cherry.jpg', 10, 7000, N'drink', CAST(N'2021-04-20 08:57:56.590' AS DateTime), 0)
INSERT [dbo].[Foods] ([FoodID], [Name], [Description], [Image], [Quantity], [Price], [Category], [DateOfCreate], [Status]) VALUES (N'F-2', N'chickennn', N'qweqwnnn', N'chicken.jpg', 83, 31230, N'meat', CAST(N'2020-12-11 00:00:00.000' AS DateTime), 1)
INSERT [dbo].[Foods] ([FoodID], [Name], [Description], [Image], [Quantity], [Price], [Category], [DateOfCreate], [Status]) VALUES (N'F-3', N'pig', N'asdas', N'pig.jpg', 12, 12300, N'meat', CAST(N'2020-12-20 00:00:00.000' AS DateTime), 1)
INSERT [dbo].[Foods] ([FoodID], [Name], [Description], [Image], [Quantity], [Price], [Category], [DateOfCreate], [Status]) VALUES (N'F-4', N'simon', N'adasd', N'simon.jpg', 23, 42342, N'meat', CAST(N'2020-12-29 00:00:00.000' AS DateTime), 1)
INSERT [dbo].[Foods] ([FoodID], [Name], [Description], [Image], [Quantity], [Price], [Category], [DateOfCreate], [Status]) VALUES (N'F-5', N'oc', N'qwasd', N'oc-huong-xao-me.jpg', 13, 12312, N'meat', CAST(N'2021-01-11 00:00:00.000' AS DateTime), 1)
INSERT [dbo].[Foods] ([FoodID], [Name], [Description], [Image], [Quantity], [Price], [Category], [DateOfCreate], [Status]) VALUES (N'F-6', N'orange', N'yeuem', N'Orange.jpg', 23, 12314, N'fruit', CAST(N'2021-01-13 16:25:07.413' AS DateTime), 1)
INSERT [dbo].[Foods] ([FoodID], [Name], [Description], [Image], [Quantity], [Price], [Category], [DateOfCreate], [Status]) VALUES (N'F-7', N'banana', N'yeuem', N'banana.jpg', 12, 12310, N'fruit', CAST(N'2021-01-21 10:52:39.540' AS DateTime), 1)
INSERT [dbo].[Foods] ([FoodID], [Name], [Description], [Image], [Quantity], [Price], [Category], [DateOfCreate], [Status]) VALUES (N'F-8', N'avocado', N'yeuem', N'avocado.jpg', 9, 12300, N'fruit', CAST(N'2021-01-21 10:53:44.280' AS DateTime), 1)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [Quantity], [FoodID], [Price]) VALUES (N'OD-ban-1-0', N'OD-ban-1', 1, N'F-1', 23200)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [Quantity], [FoodID], [Price]) VALUES (N'OD-teo-1-0', N'OD-teo-1', 2, N'F-12', 10000)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [Quantity], [FoodID], [Price]) VALUES (N'OD-teo-1-1', N'OD-teo-1', 2, N'F-2', 31230)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [Quantity], [FoodID], [Price]) VALUES (N'OD-teo-1-2', N'OD-teo-1', 2, N'F-1', 23200)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [Quantity], [FoodID], [Price]) VALUES (N'OD-teo-2-0', N'OD-teo-2', 1, N'F-8', 12300)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [Quantity], [FoodID], [Price]) VALUES (N'OD-teo-2-1', N'OD-teo-2', 1, N'F-10', 12500)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [Quantity], [FoodID], [Price]) VALUES (N'OD-teo-3-0', N'OD-teo-3', 1, N'F-2', 31230)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [Quantity], [FoodID], [Price]) VALUES (N'OD-teo-3-1', N'OD-teo-3', 5, N'F-1', 23200)
INSERT [dbo].[OrderDetail] ([OrderDetailID], [OrderID], [Quantity], [FoodID], [Price]) VALUES (N'OD-teo-3-2', N'OD-teo-3', 1, N'F-3', 12300)
INSERT [dbo].[Orders] ([OrderID], [Username], [Total], [DateOfCreate], [Status]) VALUES (N'OD-ban-1', N'ban', 23200, CAST(N'2021-04-20 07:59:22.110' AS DateTime), 1)
INSERT [dbo].[Orders] ([OrderID], [Username], [Total], [DateOfCreate], [Status]) VALUES (N'OD-teo-1', N'teo', 128860, CAST(N'2021-04-20 08:54:43.437' AS DateTime), 1)
INSERT [dbo].[Orders] ([OrderID], [Username], [Total], [DateOfCreate], [Status]) VALUES (N'OD-teo-2', N'teo', 24800, CAST(N'2021-04-20 08:56:13.227' AS DateTime), 1)
INSERT [dbo].[Orders] ([OrderID], [Username], [Total], [DateOfCreate], [Status]) VALUES (N'OD-teo-3', N'teo', 159530, CAST(N'2021-04-20 09:16:35.410' AS DateTime), 1)
INSERT [dbo].[Registration] ([Username], [Password], [Fullname], [Role], [Status]) VALUES (N'an', N'123', N'Ann', N'admin', 1)
INSERT [dbo].[Registration] ([Username], [Password], [Fullname], [Role], [Status]) VALUES (N'anhnguyentien3006@gmail.com', N'123', N'anhnguyentien3006@gmail.com', N'user', 1)
INSERT [dbo].[Registration] ([Username], [Password], [Fullname], [Role], [Status]) VALUES (N'antdpse140753@fpt.edu.vn', N'123', N'antdpse140753@fpt.edu.vn', N'admin', 1)
INSERT [dbo].[Registration] ([Username], [Password], [Fullname], [Role], [Status]) VALUES (N'ban', N'123', N'ban', N'user', 1)
INSERT [dbo].[Registration] ([Username], [Password], [Fullname], [Role], [Status]) VALUES (N'kythi2000@gmail.com', N'123', N'kythi2000@gmail.com', N'user', 1)
INSERT [dbo].[Registration] ([Username], [Password], [Fullname], [Role], [Status]) VALUES (N'teo', N'123', N'nguyen van teo', N'user', 1)
INSERT [dbo].[Registration] ([Username], [Password], [Fullname], [Role], [Status]) VALUES (N'ty', N'123', N'nguyen ty', N'admin', 1)
INSERT [dbo].[Registration] ([Username], [Password], [Fullname], [Role], [Status]) VALUES (N'yeuem', N'123', N'Yeu em la dieu anh khong the ngo', N'user', 1)
INSERT [dbo].[Updation] ([UpdationID], [Username], [FoodID], [DateOfCreate], [Action]) VALUES (N'Updation-1', N'an', N'F-1', CAST(N'2021-01-21 10:05:44.410' AS DateTime), N'Update')
INSERT [dbo].[Updation] ([UpdationID], [Username], [FoodID], [DateOfCreate], [Action]) VALUES (N'Updation-10', N'an', N'F-1', CAST(N'2021-01-21 16:02:35.580' AS DateTime), N'Update')
INSERT [dbo].[Updation] ([UpdationID], [Username], [FoodID], [DateOfCreate], [Action]) VALUES (N'Updation-11', N'ty', N'F-13', CAST(N'2021-04-20 08:58:19.217' AS DateTime), N'Update')
INSERT [dbo].[Updation] ([UpdationID], [Username], [FoodID], [DateOfCreate], [Action]) VALUES (N'Updation-12', N'ty', N'F-13', CAST(N'2021-04-20 08:58:33.773' AS DateTime), N'Delete')
ALTER TABLE [dbo].[Foods]  WITH CHECK ADD  CONSTRAINT [FK_Foods_Category] FOREIGN KEY([Category])
REFERENCES [dbo].[Category] ([Category])
GO
ALTER TABLE [dbo].[Foods] CHECK CONSTRAINT [FK_Foods_Category]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Foods] FOREIGN KEY([FoodID])
REFERENCES [dbo].[Foods] ([FoodID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Foods]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Orders] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Orders] ([OrderID])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Orders]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Registration] FOREIGN KEY([Username])
REFERENCES [dbo].[Registration] ([Username])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Registration]
GO
ALTER TABLE [dbo].[Updation]  WITH CHECK ADD  CONSTRAINT [FK_Updation_Registration] FOREIGN KEY([Username])
REFERENCES [dbo].[Registration] ([Username])
GO
ALTER TABLE [dbo].[Updation] CHECK CONSTRAINT [FK_Updation_Registration]
GO
USE [master]
GO
ALTER DATABASE [HanaShop] SET  READ_WRITE 
GO
