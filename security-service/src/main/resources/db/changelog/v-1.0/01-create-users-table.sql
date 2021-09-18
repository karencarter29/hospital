create table users (
   id VARCHAR(255) not null,
    password varchar(255) not null,
    username varchar(255) not null,
   first_name varchar(255) not null,
   second_name varchar(255) not null,
    primary key (id)
) engine=InnoDB

GO

alter table users
   add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username)
GO