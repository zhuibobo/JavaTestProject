create TABLE person(
  id int PRIMARY KEY,
  name VARCHAR(50) not NULL ,
  sex VARCHAR(10) not NULL ,
  age int,
  idcard VARCHAR(50) not NULL,
  brithday DATETIME
);
GO

create TABLE person_field_to_property(
  id int PRIMARY KEY,
  name VARCHAR(50) not NULL ,
  sex VARCHAR(10) not NULL ,
  age int,
  idcard VARCHAR(50) not NULL,
  brithday DATETIME
);
GO

create TABLE relationship_1to1_pc(
  id int PRIMARY KEY,
  name VARCHAR(50) not NULL,
  cpu_id VARCHAR(10) not NULL
);

create TABLE relationship_1to1_cpu(
  id int PRIMARY KEY,
  name VARCHAR(50) not NULL
);

go

create TABLE relationship_1toN_pc(
  id int PRIMARY KEY,
  name VARCHAR(50) not NULL
);

create TABLE relationship_1toN_cpu(
  id int PRIMARY KEY,
  name VARCHAR(50) not NULL,
  pc_id int not null,
);

go

--不带参数的存储过程--
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sp_insert_relationship_1to1_pc]') AND type in (N'P', N'PC'))
  DROP PROCEDURE [dbo].[sp_insert_relationship_1to1_pc]
GO

create procedure sp_insert_relationship_1to1_pc
    @id int,
    @name varchar(50),
    @cpuid varchar(50)
as
  begin
    insert into relationship_1to1_pc(id,name,cpu_id) values (@id,@name,@cpuid);
  end;
go

--带输出参数的存储过程--
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[sp_insert_relationship_1to1_pc_out_param]') AND type in (N'P', N'PC'))
  DROP PROCEDURE [dbo].[sp_insert_relationship_1to1_pc_out_param]
GO

create procedure sp_insert_relationship_1to1_pc_out_param
    @id int,
    @name varchar(50),
    @cpuid varchar(50),
    @outname varchar(50) out
as
  begin
    insert into relationship_1to1_pc(id,name,cpu_id) values (@id,@name,@cpuid);
    select @outname='out'+name from relationship_1to1_pc;
    select @outname outname_1;
  end;
go