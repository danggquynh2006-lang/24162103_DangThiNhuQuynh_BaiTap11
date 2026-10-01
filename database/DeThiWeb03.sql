-- Chay file nay trong SSMS (SQL Server) TRUOC khi chay web.
IF DB_ID('DeThiWeb03') IS NULL CREATE DATABASE DeThiWeb03;
GO
USE DeThiWeb03;
GO
IF OBJECT_ID('Favorites') IS NOT NULL DROP TABLE Favorites;
IF OBJECT_ID('Shares')    IS NOT NULL DROP TABLE Shares;
IF OBJECT_ID('Videos')    IS NOT NULL DROP TABLE Videos;
IF OBJECT_ID('Category')  IS NOT NULL DROP TABLE Category;
IF OBJECT_ID('Users')     IS NOT NULL DROP TABLE Users;
GO
CREATE TABLE Users(
  Username nvarchar(50) PRIMARY KEY, Password nvarchar(50), Phone nvarchar(15),
  Fullname nvarchar(50), Email nvarchar(150), Admin bit, Active bit, Images nvarchar(500));
CREATE TABLE Category(
  CategoryId int IDENTITY(1,1) PRIMARY KEY, Categoryname nvarchar(100),
  Categorycode nvarchar(100), Images nvarchar(500), Status bit);
CREATE TABLE Videos(
  VideoId nvarchar(50) PRIMARY KEY, Title nvarchar(200), Poster nvarchar(50),
  Views int, Description nvarchar(500), Active bit,
  CategoryId int REFERENCES Category(CategoryId));
CREATE TABLE Shares(
  ShareId int IDENTITY(1,1) PRIMARY KEY, Emails nvarchar(50), SharedDate date,
  Username nvarchar(50) REFERENCES Users(Username),
  VideoId nvarchar(50) REFERENCES Videos(VideoId));
CREATE TABLE Favorites(
  FavoriteId int IDENTITY(1,1) PRIMARY KEY, LikedDate date,
  VideoId nvarchar(50) REFERENCES Videos(VideoId),
  Username nvarchar(50) REFERENCES Users(Username));
GO
-- Tai khoan: admin / admin123 (quan tri)  |  user1 / 123456 (nguoi dung)
INSERT Users VALUES
 (N'admin',N'admin123',N'0900000001',N'Quản trị viên',N'admin@gmail.com',1,1,NULL),
 (N'user1',N'123456',N'0900000002',N'Nguyễn Văn A',N'user1@gmail.com',0,1,NULL),
 (N'user2',N'123456',N'0900000003',N'Trần Thị B',N'user2@gmail.com',0,1,NULL);
INSERT Category(Categoryname,Categorycode,Images,Status) VALUES
 (N'Nước hoa nam',N'MEN',NULL,1),
 (N'Nước hoa nữ',N'WOMEN',NULL,1),
 (N'Nước hoa unisex',N'UNISEX',NULL,1);
INSERT Videos VALUES
 (N'V01',N'Dior Sauvage',N'dior.jpg',1250,N'Hương thơm nam tính, mạnh mẽ.',1,1),
 (N'V02',N'Chanel Bleu',N'chanel.jpg',980,N'Sang trọng, tinh tế.',1,1),
 (N'V03',N'Gucci Guilty',N'gucci.jpg',760,N'Quyến rũ, cá tính.',1,1),
 (N'V04',N'YSL Y',N'ysl.jpg',540,N'Trẻ trung, năng động.',1,1),
 (N'V05',N'Versace Eros',N'blue.jpg',430,N'Nồng nàn, lôi cuốn.',1,1),
 (N'V06',N'Bleu de Chanel EDP',N'chanel.jpg',300,N'Gỗ ấm, cay nhẹ.',1,1),
 (N'V07',N'Lancôme La Vie Est Belle',N'lancome.jpg',1500,N'Ngọt ngào, nữ tính.',1,2),
 (N'V08',N'Chanel No.5',N'chanel.jpg',2100,N'Biểu tượng kinh điển.',1,2),
 (N'V09',N'Dior Miss Dior',N'dior.jpg',890,N'Hoa cỏ tươi mát.',1,2),
 (N'V10',N'Gucci Bloom',N'gucci.jpg',610,N'Hương hoa trắng.',1,2),
 (N'V11',N'YSL Libre',N'ysl.jpg',720,N'Tự do, phóng khoáng.',1,2),
 (N'V12',N'Lancôme Idôle',N'lancome.jpg',350,N'Thanh lịch, hiện đại.',1,2),
 (N'V13',N'Dior Hypnotic Poison',N'dior.jpg',410,N'Quyến rũ bí ẩn.',1,2),
 (N'V14',N'Jo Malone Wood Sage',N'blue.jpg',260,N'Gỗ và muối biển.',1,3),
 (N'V15',N'Le Labo Santal 33',N'gucci.jpg',330,N'Hương gỗ đàn hương.',1,3),
 (N'V16',N'Tom Ford Neroli',N'ysl.jpg',280,N'Cam chanh tươi sáng.',1,3),
 (N'V17',N'Byredo Blanche',N'chanel.jpg',190,N'Trắng tinh khôi.',1,3);
INSERT Shares(Emails,SharedDate,Username,VideoId) VALUES
 (N'a@gmail.com','2026-09-01',N'user1',N'V01'),
 (N'b@gmail.com','2026-09-02',N'user1',N'V01'),
 (N'c@gmail.com','2026-09-03',N'user2',N'V01'),
 (N'd@gmail.com','2026-09-04',N'user2',N'V07'),
 (N'e@gmail.com','2026-09-05',N'user1',N'V08');
INSERT Favorites(LikedDate,VideoId,Username) VALUES
 ('2026-09-01',N'V01',N'user1'),('2026-09-01',N'V01',N'user2'),
 ('2026-09-02',N'V02',N'user1'),('2026-09-03',N'V07',N'user1'),
 ('2026-09-03',N'V07',N'user2'),('2026-09-04',N'V08',N'user1');
GO
