create TABLE person(
  id int PRIMARY KEY,
  name VARCHAR(50) not NULL ,
  sex VARCHAR(10) not NULL ,
  age int,
  idcard VARCHAR(50) not NULL,
  brithday DATETIME
);
GO

create TABLE person_auto_id(
  pid int PRIMARY KEY IDENTITY,
  name VARCHAR(50) not NULL ,
  sex VARCHAR(10) not NULL ,
  age int,
  idcard VARCHAR(50) not NULL,
  brithday DATETIME
);
GO

create procedure sp_insert_person_not_out
    @id int,
    @name varchar(50),
    @sex varchar(10),
    @age int,
    @idcard varchar(50),
    @brithday datetime
as
  begin
    insert into person(id,name,sex,age,idcard,brithday) values (@id,@name,@sex,@age,@idcard,@brithday);
  end;
go

--delete person;

--exec sp_insert_person_not_out 1,'name','男',18,'445102','1982-06-12';

--select * from person;

create procedure sp_insert_person_out_param
    @id int,
    @name varchar(50),
    @sex varchar(10),
    @age int,
    @idcard varchar(50),
    @brithday datetime,
    @outidcard varchar(50) out,
    @outname varchar(50) output
as
  begin
    insert into person(id,name,sex,age,idcard,brithday) values (@id,@name,@sex,@age,@idcard,@brithday);
    select @outidcard=idcard,@outname=name from person where id=@id
  end;
go

--delete person;

--declare @outidcard varchar(50);
--declare @outname varchar(50);
--exec sp_insert_person_out_param 1,'name','男',18,'445102','1982-06-12',@outidcard out,@outname out;
--select @outidcard outidcard, @outname outname;

create proc sp_select_person_cursor
    @cur cursor varying output
as
  set @cur = cursor forward_only static for
  select * from person;
  open @cur;
go

create proc sp_select_person
as
  select * from dbo.person
go

create proc sp_select_person_2_result
as
  select * from dbo.person;
  select 'hellow' newc,* from dbo.person;
go

exec sp_select_person_2_result;