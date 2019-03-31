CREATE TABLE Company(       --FIRST TO CREATE
nameC VARCHAR(30) PRIMARY KEY,
password VARCHAR(30) NOT NULL,
accepted_students_email VARCHAR(30),
accepted_students_name VARCHAR(30)
);

drop table Company;

CREATE TABLE Student(
name VARCHAR ,
password VARCHAR(30) NOT NULL,
email VARCHAR(30),
 PRIMARY KEY(email)
);

drop table Student;

CREATE TABLE Internship(
ID  SERIAL  ,
title VARCHAR,
companyName VARCHAR(30) REFERENCES Company(nameC),
specialization VARCHAR NOT NULL,
start_date VARCHAR,
duration INT,
description VARCHAR,
PRIMARY KEY(ID));

drop table Internship;

CREATE TABLE Applications(
cv BYTEA,
letter BYTEA,
email VARCHAR(30),
ID_internship int REFERENCES Internship(ID),
FOREIGN KEY(email) REFERENCES Student(email),
PRIMARY KEY(email,ID_internship)
);

drop table Applications;

CREATE TABLE Accepted (
email VARCHAR(30),
ID_internship int REFERENCES Internship(ID),
response VARCHAR,
FOREIGN KEY(email) REFERENCES Student(email),
PRIMARY KEY(email,ID_internship,response)
);

drop table Accepted;








