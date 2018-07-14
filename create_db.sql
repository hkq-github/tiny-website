CREATE DATABASE TinyDatabase;
USE TinyDatabase;

CREATE TABLE user(
    id  CHAR(10) PRIMARY KEY,
    salt CHAR(16) NOT NULL,
    pass CHAR(64) NOT NULL,
    freeze BIT NOT NULL,
    name CHAR(10) NOT NULL,
    email VARCHAR(60) UNIQUE NOT NULL,
    sex CHAR(2) NOT NULL,
    intro VARCHAR(256) NOT NULL
);

CREATE TABLE admin(
    id CHAR(10) PRIMARY KEY,
    pass CHAR(16) NOT NULL
);

/* 管理员 */
INSERT INTO admin VALUE('admin', 'admin888');