s/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/6/5 22:03:14                            */
/*==============================================================*/


drop table if exists Class;

drop table if exists Course;

drop table if exists Dictionary;

drop table if exists DictionaryDetail;

drop table if exists Menu;

drop table if exists Role;

drop table if exists RoleMenu;

drop table if exists Score;

drop table if exists Sign_Student;

drop table if exists Sign_teacher;

drop table if exists User;

drop table if exists UserRole;

drop table if exists Usersfeedback;

drop table if exists joinclass;

/*==============================================================*/
/* Table: Class                                                 */
/*==============================================================*/
create table Class
(
   Id                   int not null auto_increment,
   Course_Id            int not null,
   Class_No             varchar(255) not null,
   Class_name           varchar(32) not null,
   Class_Place          varchar(32) not null,
   progess              int,
   Teacher_Id           varchar(32),
   Teacher_Name         varchar(32),
   LastSignTime         datetime,
   Createby             varchar(16) not null,
   Createdate           datetime not null,
   Modifyby             varchar(16),
   Modifydate           timestamp
);

/*==============================================================*/
/* Table: Course                                                */
/*==============================================================*/
create table Course
(
   Id                   int not null auto_increment,
   Course_Name          varchar(255) not null,
   School               varchar(255) not null,
   Institute            varchar(255) not null,
   Course_Term          varchar(255) not null,
   Course_Week          varchar(255) not null,
   Course_Require       varchar(255),
   Course_Proceed       varchar(255) not null,
   Course_Exam          varchar(128),
   Createby             varchar(16) not null,
   Createdate           datetime not null,
   Modifyby             varchar(16),
   Modifydate           datetime
);

/*==============================================================*/
/* Table: Dictionary                                            */
/*==============================================================*/
create table Dictionary
(
   Id                   int not null auto_increment,
   Code                 varchar(16) not null,
   Description          varchar(256) not null,
   Createby             varchar(16) not null,
   Createdate           datetime not null,
   Modifyby             varchar(16),
   Modifydate           datetime
);

/*==============================================================*/
/* Table: DictionaryDetail                                      */
/*==============================================================*/
create table DictionaryDetail
(
   Id                   int not null auto_increment,
   Dictionary_Id        int,
   ItemKey              int not null,
   ItemValue            varchar(32) not null,
   IsDefault            bit not null,
   Createby             varchar(16) not null,
   Createdate           datetime not null,
   Modifyby             varchar(16),
   Modifydate           datetime
);

/*==============================================================*/
/* Table: Menu                                                  */
/*==============================================================*/
create table Menu
(
   Id                   int not null auto_increment,
   Parent_Id            int,
   Menu_Name            varchar(16) not null,
   Url                  varchar(256) not null,
   Icon                 varchar(256) not null,
   Createby             varchar(16) not null,
   Createdate           datetime not null,
   Modifyby             varchar(16),
   Modifydate           datetime
);

/*==============================================================*/
/* Table: Role                                                  */
/*==============================================================*/
create table Role
(
   Id                   int not null auto_increment,
   Role_Name            varchar(16) not null,
   Role_Code            varchar(256),
   Createby             varchar(16) not null,
   Createdate           datetime not null,
   Modifyby             varchar(16),
   Modifydate           datetime
);

/*==============================================================*/
/* Table: RoleMenu                                              */
/*==============================================================*/
create table RoleMenu
(
   Id                   int(16) not null auto_increment,
   Role_Id              int(16),
   Menu_Id              int(16),
   Createby             varchar(16) not null,
   Createdate           datetime not null,
   Modifyby             varchar(16),
   Modifydate           datetime
);

/*==============================================================*/
/* Table: Score                                                 */
/*==============================================================*/
create table Score
(
   Id                   int not null auto_increment,
   User_Id              int not null,
   Class_Id             int not null,
   Coursescore          double not null,
   Examscore            double not null,
   Finalscore           double not null,
   Createby             varchar(16) not null,
   Createdate           datetime not null,
   Modifyby             varchar(16),
   Modifydate           datetime
);

/*==============================================================*/
/* Table: Sign_Student                                          */
/*==============================================================*/
create table Sign_Student
(
   Id                   int not null auto_increment,
   Class_No             varchar(255) not null,
   telephone            varchar(255),
   Sign_teacher_Id      int,
   Student_Place        varchar(255),
   Student_Time         datetime,
   Distance             varchar(255),
   SignState            int,
   Createby             varchar(16) not null,
   Createdate           datetime not null,
   Modifyby             varchar(16),
   Modifydate           datetime
);

/*==============================================================*/
/* Table: Sign_teacher                                          */
/*==============================================================*/
create table Sign_teacher
(
   Id                   int not null auto_increment,
   Class_No             varchar(255) not null,
   Teacher_Place        varchar(255),
   Teacher_Time         datetime,
   Signing_State        int,
   Createby             varchar(16) not null,
   Createdate           datetime not null,
   Modifyby             varchar(16),
   Modifydate           datetime
);

/*==============================================================*/
/* Table: User                                                  */
/*==============================================================*/
create table User
(
   Id                   int not null auto_increment,
   User_No              varchar(16) not null,
   User_Type            varchar(16) not null,
   User_Name            varchar(16) not null,
   User_Telephone       varchar(16) not null,
   User_Pass            varchar(255),
   User_Sex             varchar(16),
   User_Born            varchar(16),
   User_School          varchar(16) not null,
   User_Institute       varchar(16) not null,
   Createby             varchar(16) not null,
   Createdate           datetime not null,
   Modifyby             varchar(16) not null,
   Modifydate           datetime not null
);

/*==============================================================*/
/* Table: UserRole                                              */
/*==============================================================*/
create table UserRole
(
   Id                   int not null auto_increment,
   Role_Id              int,
   User_Id              int not null,
   Createby             varchar(16) not null,
   Createdate           datetime not null,
   Modifyby             varchar(16),
   Modifydate           datetime
);

/*==============================================================*/
/* Table: Usersfeedback                                         */
/*==============================================================*/
create table Usersfeedback
(
   Id                   int not null auto_increment,
   Role                 varchar(16),
   telephone            varchar(16),
   Content              text,
   Createby             varchar(16) not null,
   Createdate           datetime not null,
   Modifyby             varchar(16),
   Modifydate           datetime
);

/*==============================================================*/
/* Table: joinclass                                             */
/*==============================================================*/
create table joinclass
(
   Id                   int not null,
   Class_No             varchar(255) not null,
   telephone            varchar(16) not null,
   Createby             varchar(16) not null,
   Createdate           datetime not null,
   Modifyby             varchar(16),
   Modifydate           datetime
);

alter table Class add constraint FK_Reference_18 foreign key (Course_Id)
      references Course (Id) on delete restrict on update restrict;

alter table DictionaryDetail add constraint FK_Reference_3 foreign key (Dictionary_Id)
      references Dictionary (Id) on delete restrict on update restrict;

alter table Menu add constraint FK_Reference_1 foreign key (Parent_Id)
      references Menu (Id) on delete restrict on update restrict;

alter table RoleMenu add constraint FK_Reference_15 foreign key (Role_Id)
      references Role (Id) on delete restrict on update restrict;

alter table RoleMenu add constraint FK_Reference_16 foreign key (Menu_Id)
      references Menu (Id) on delete restrict on update restrict;

alter table Score add constraint FK_Reference_13 foreign key (User_Id)
      references User (Id) on delete restrict on update restrict;

alter table Score add constraint FK_Reference_17 foreign key (Class_Id)
      references Class (Id) on delete restrict on update restrict;

alter table Sign_Student add constraint FK_Reference_21 foreign key (telephone)
      references User (User_Telephone) on delete restrict on update restrict;

alter table Sign_Student add constraint FK_Reference_22 foreign key (Sign_teacher_Id)
      references Sign_teacher (Id) on delete restrict on update restrict;

alter table Sign_teacher add constraint FK_Reference_20 foreign key (Class_No)
      references Class (Class_No) on delete restrict on update restrict;

alter table UserRole add constraint FK_Reference_5 foreign key (User_Id)
      references User (Id) on delete restrict on update restrict;

alter table UserRole add constraint FK_Reference_6 foreign key (Role_Id)
      references Role (Id) on delete restrict on update restrict;

alter table Usersfeedback add constraint FK_Reference_23 foreign key (telephone)
      references User (User_Telephone) on delete restrict on update restrict;

alter table joinclass add constraint FK_Reference_14 foreign key (telephone)
      references User (User_Telephone) on delete restrict on update restrict;

alter table joinclass add constraint FK_Reference_19 foreign key (Class_No)
      references Class (Class_No) on delete restrict on update restrict;

