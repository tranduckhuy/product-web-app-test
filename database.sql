-- DROP DATABASE productdb;

CREATE DATABASE IF NOT EXISTS productdb DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE productdb;

CREATE TABLE IF NOT EXISTS user
(
	id varchar(64) not null,
    email nvarchar(255) not null,
    password varchar(64) not null,
    name varchar(255) not null,
    created_at datetime,
    
    primary key(id)
);

CREATE TABLE IF NOT EXISTS product
(
	id varchar(255) not null,
    name varchar(255) not null,
    description nvarchar(255),
    image nvarchar(255),
	category varchar(255),
    created_at datetime,

    primary key(id)
);

-- default data 
INSERT INTO user (id, email, password, name, created_at) 
VALUES 
	('U1', 'admin@gmail.com', '1oe5r4CSU0CCE5Hfcc0E68vmIIuysaqJS8FcWcUnqK8=', 'Admin', '2023-10-21 15:16:11');



