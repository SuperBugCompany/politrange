drop database if exists politrange;
create database politrange; 
use politrange;                                              

create table Persons (
  ID        int             not null   auto_increment, 
  Name      varchar(2048)   not null,

  primary key   (ID)
) character set utf8;

create table Keywords (
  ID        int             not null   auto_increment,
  Name      varchar(2048)   not null,
  PersonID  int             not null,
  
  primary key   (ID),
  foreign key   (PersonID) references Persons(ID) 
) character set utf8;

create table Sites (
  ID        int             not null   auto_increment,
  Name      varchar(2048)   not null,
  
  primary key   (ID)
) character set utf8;

create table Pages (
  ID            int            not null 	auto_increment,
  URL           nvarchar(2048) not null,
  SiteID        int            not null,
  FoundDateTime datetime       not null,
  LastScanDate  datetime       not null,
  
  primary key   (ID),
  foreign key   (SiteID) references Sites(ID)  
) character set utf8;

create table PersonPageRank (
  PersonID    int             not null,
  PageID      int             not null,
  Rank        int             not null,
  
  primary key   (PersonID),
  foreign key   (PersonID) 	references Persons(ID),
  foreign key   (PageID) 	  references Pages(ID) 
) character set utf8;

-- создание остальных таблиц ...
-- пользователи...

create table Roles (
  ID        int             not null  auto_increment,
  Role      varchar(2048)   not null,
  
  primary key   (Id)
) character set utf8;

create table Users (
  ID        int             not null  auto_increment,
  Name      varchar(2048)   not null,
  RoleId    int             not null,	
  
  primary key   (ID),
  foreign key   (RoleId) references Roles(ID)
  ) character set utf8;


insert into Persons( ID, Name ) 
values ('1','Медведев'),('2','Навальный'),('3','Путин');

insert into Keywords( ID, Name, PersonID ) 
values ('1','Медведев','1'),('2','Медведева','1'),('3','Медведеву','1');

insert into PersonPageRank( PersonID, PageID, Rank ) 
values ('1','1','1'), ('2','1','1'), ('3','1','1');

insert into Sites( ID, Name ) 
values ('1','http://lenta.ru/'),('2','http://www.gazeta.ru/'),('3','http://www.vedomosti.ru/');

insert into Pages( ID, URL, SiteID, FoundDateTime, LastScanDate ) 
values ('1','http://lenta.ru/news/2015/11/21/condolescenes/','1','21.11.2015','21.11.2015');

insert into Roles( ID, Role ) 
values ('1','Boss'),('2','Staff');

insert into Users( ID, Name, RoleId ) 
values ('1','Менеджер', '1'),('2','Аналитик', '2');





