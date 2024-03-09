DROP TABLE IF EXISTS Account;

CREATE TABLE account (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  phone_num VARCHAR(250) NOT NULL,
  balance_amt INT NOT NULL,
  secure_token VARCHAR(250) NOT NULL
);

insert into account (id,first_name,last_name,phone_num,balance_amt,secure_token) values (1,'Mahesh','More','1234567890',100000,'1234');
insert into account (id,first_name,last_name,phone_num,balance_amt,secure_token) values(2,'Megha','Bijlani','0987654321',200000,'1234');
insert into account (id,first_name,last_name,phone_num,balance_amt,secure_token) values(3,'Anmol','Narang','0147258369',300000,'1234');
