DROP TABLE IF EXISTS Account;

CREATE TABLE User (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_Name VARCHAR(250) NOT NULL,
  last_Name VARCHAR(250) NOT NULL,
  phone_Num VARCHAR(250) NOT NULL,
  secure_Token VARCHAR(250) NOT NULL
);

insert into user(id,first_name,last_name,phone_num,secure_token) values (1,'Mahesh','More','1234567890','1234');
insert into user(id,first_name,last_name,phone_num,secure_token) values(2,'Megha','Bijlani','0987654321','1234');
insert into user(id,first_name,last_name,phone_num,secure_token) values(3,'Anmol','Narang','0147258369','1234');

CREATE TABLE Qestions (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  question VARCHAR(250) NOT NULL,
  answer VARCHAR(250) NOT NULL,
  user_id INT NOT NULL,
);

insert into questions(id,question,answer,user_id) values (1,'What is pet name of your best friend','lucy',1);
insert into questions(id,question,answer,user_id) values (2,'What is your favourite travel destination','london',1);
insert into questions(id,question,answer,user_id) values (3,'What is your first school teacher name','Maria',1);

insert into questions(id,question,answer,user_id) values (4,'What is pet name of your best friend','randy',2);
insert into questions(id,question,answer,user_id) values (5,'What is your favourite travel destination','manali',2);
insert into questions(id,question,answer,user_id) values (6,'What is your first school teacher name','Sofi',2);

insert into questions(id,question,answer,user_id) values (7,'What is pet name of your best friend','tommy',3);
insert into questions(id,question,answer,user_id) values (8,'What is your favourite travel destination','kerala',3);
insert into questions(id,question,answer,user_id) values (9,'What is your first school teacher name','Leo',3);

