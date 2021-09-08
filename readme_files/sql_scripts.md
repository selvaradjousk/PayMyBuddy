# Pay My Buddy - APPLICATION DEVELOPMENT  - SQL SCRIPTS
<p><b>PayMyBuddy</b> We make moving your money easy!</p>


![UML_Diagram](../assets/paymybuddy_banner_image1.png "Pay My Buddy Banner")


<p> The purpose of the project is to build an App that would allow customers to transfer money, to manage their finances or pay their friends with atmost ease. </p>

   

SQL Scripts
===

[click here to access](../assets/sql_scripts/paymybuddy_maindb.sql) <-- SQL Scripts<br /><br /><br />

![DB Setup](../assets/sql_scripts/DB_sql_application_properties_setup.png "DB Setup")

-- -----------------------------------------------------
###  CREATION USER pmb_user with privilages to database: paymybuddy_maindb 
-- -----------------------------------------------------
```sql
CREATE USER 'pmb_user'@'localhost' IDENTIFIED BY 'pmb_pass';
GRANT ALL PRIVILEGES ON paymybuddy_maindb.* TO 'pmb_user'@'localhost' WITH GRANT OPTION;
```
-- -----------------------------------------------------
-- -----------------------------------------------------
-- -----------------------------------------------------
### -- SCHEMA TO CREATE DATABASE FOR TESTING = (Schema.sql))
-- -----------------------------------------------------
-- -----------------------------------------------------
-- -----------------------------------------------------

### CREATION OF DATABASE NAMED: paymybuddy_maindb 
-- ----------------------------------- --

-- -----------------------------------------------------
### -- DATABASE SCHEMA DEFINITION
-- -----------------------------------------------------

```sql
-- DROP DATABASE if exists `paymybuddy_maindb`;
-- CREATE DATABASE `paymybuddy_maindb`;
```

```sql
USE `paymybuddy_maindb` ;
```


### Droping the existing data during intialisation of database paymybuddy_maindb 

```sql
DROP TABLE if exists `bank_account`;
DROP TABLE if exists `transaction`;
DROP TABLE if exists `contact`;
DROP TABLE if exists `transfer`;
DROP TABLE if exists `users`;
```
-- ----------------------------------------------------
### -- CREATING the Table user
-- -----------------------------------------------------

```sql
CREATE TABLE `user` (
	`id_user` INT NOT NULL AUTO_INCREMENT,
	`user_name` VARCHAR(50) NOT NULL,
	`first_name` VARCHAR(50) NOT NULL,
	`last_name` VARCHAR(50) NOT NULL,
	`email` VARCHAR(50) NOT NULL,
	`password` VARCHAR(100) NOT NULL,
	`creation_date` DATE NOT NULL,
	`modification_date` DATE NULL DEFAULT NULL,
	`roles` VARCHAR(20) NOT NULL,
	`active` BIT(1) NULL DEFAULT b'1',
	`walletAmount` DOUBLE NOT NULL DEFAULT 0.0,
 PRIMARY KEY  (`id_user`),
 UNIQUE KEY `UK_users_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
```

-- -----------------------------------------------------
### -- CREATING the Table bank_account
-- -----------------------------------------------------

```sql
CREATE TABLE IF NOT EXISTS `paymybuddy_maindb`.`bank_account` (
	`id_bank_account` INT NOT NULL AUTO_INCREMENT,
	`user_id` INT NOT NULL,
	`rib` VARCHAR(255) NOT NULL,
 PRIMARY KEY  (`id_bank_account`),
 CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
```

-- -----------------------------------------------------
### -- CREATING the Table transaction
-- -----------------------------------------------------

```sql
CREATE TABLE IF NOT EXISTS `paymybuddy_maindb`.`transaction` (
  `id_transaction` INT NOT NULL AUTO_INCREMENT,
  `payer_id` INT NOT NULL,
  `beneficiary_id` INT NOT NULL,
  `amount` DOUBLE NOT NULL,
  `description` VARCHAR(200) NOT NULL,
  `date` DATETIME NULL DEFAULT NULL,
  `commision` DOUBLE NOT NULL,
 PRIMARY KEY (`id_transaction`),
 CONSTRAINT `fk_beneficiary` FOREIGN KEY (`beneficiary_id`) REFERENCES `user` (`id_user`),
 CONSTRAINT `fk_payer` FOREIGN KEY (`payer_id`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

```


-- -----------------------------------------------------
### -- CREATING the Table transfer
-- -----------------------------------------------------

```sql
CREATE TABLE IF NOT EXISTS `paymybuddy_maindb`.`transfer` (
  `id_transfer` INT NOT NULL AUTO_INCREMENT,
  `rib` VARCHAR(255) NOT NULL,
  `date` DATE NOT NULL,
  `amount` DOUBLE NOT NULL,
  `type` ENUM('CREDIT', 'DEBIT') NOT NULL,
  `user_id` INT NOT NULL,
 PRIMARY KEY (`id_transfer`),
 CONSTRAINT `fk_user_transfer` FOREIGN KEY (`user_id`) REFERENCES user(`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
```

-- -----------------------------------------------------
### -- CREATING the Table contact
-- -----------------------------------------------------

```sql
CREATE TABLE IF NOT EXISTS `paymybuddy_maindb`.`contact` (
	`id_contact` INT NOT NULL AUTO_INCREMENT,
	`creation_date` DATE NOT NULL,
	`payer_id` INT NOT NULL,
	`contact_id` INT NOT NULL,
 PRIMARY KEY  (`id_contact`),
 CONSTRAINT `fk_payer` FOREIGN KEY (`payer_idv) REFERENCES `user` (`id_user`),
 CONSTRAINT `fk_contact` FOREIGN KEY (`contact_id`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
```





-- -----------------------------------------------------
-- -----------------------------------------------------
### -- DATA TO POPULATE THE DATABASE FOR TESTING = (Data.sql))
-- -----------------------------------------------------
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Table `paymybuddy_maindb`.`user`  Bcrypt password = password
-- -----------------------------------------------------
-- -----------------------------------------------------

```sql
INSERT INTO 'user' ('user_name', 'first_name', 'last_name', 'email', 'password', 'creation_date', 'modification_date', 'roles', 'active', 'wallet_amount') values
	('testusername1', 'testfirstname1', 'testlastname1', 'testemail1@email.com', '$2a$10$el8am2L8Jr83rPDagpPzPOJ3.z5iE9LxaGHLsPzKtpoYk.f8K6DnW', '2021-08-26', '2021-08-26', 'admin', true, 1000.0);
    	('testusername2', 'testfirstname2', 'testlastname2', 'testemail2@email.com', '$2a$10$el8am2L8Jr83rPDagpPzPOJ3.z5iE9LxaGHLsPzKtpoYk.f8K6DnW',  '2021-08-26', '2021-08-26', 'user', true, 2000.0);
    	('testusername3', 'testfirstname3', 'testlastname3', 'testemail3@email.com', '$2a$10$el8am2L8Jr83rPDagpPzPOJ3.z5iE9LxaGHLsPzKtpoYk.f8K6DnW',  '2021-08-26', '2021-08-26', 'user', true, 3000.0);
    	('testusername4', 'testfirstname4', 'testlastname4', 'testemail4@email.com', '$2a$10$el8am2L8Jr83rPDagpPzPOJ3.z5iE9LxaGHLsPzKtpoYk.f8K6DnW',  '2021-08-26', '2021-08-26', 'user', true, 4000.0);
    	('testusername5', 'testfirstname5', 'testlastname5', 'testemail5@email.com', '$2a$10$el8am2L8Jr83rPDagpPzPOJ3.z5iE9LxaGHLsPzKtpoYk.f8K6DnW',  '2021-08-26', '2021-08-26', 'user', true, 5000.0);
    	('testusername6', 'testfirstname6', 'testlastname6', 'testemail6@email.com', '$2a$10$el8am2L8Jr83rPDagpPzPOJ3.z5iE9LxaGHLsPzKtpoYk.f8K6DnW',  '2021-08-26', '2021-08-26', 'user', true, 6000.0);
    	('testusername7', 'testfirstname7', 'testlastname7', 'testemail7@email.com', '$2a$10$el8am2L8Jr83rPDagpPzPOJ3.z5iE9LxaGHLsPzKtpoYk.f8K6DnW',  '2021-08-26', '2021-08-26', 'user', true, 7000.0);
    	('testusername8', 'testfirstname8', 'testlastname8', 'testemail8@email.com', '$2a$10$el8am2L8Jr83rPDagpPzPOJ3.z5iE9LxaGHLsPzKtpoYk.f8K6DnW',  '2021-08-26', '2021-08-26', 'user', true, 8000.0);
    	('testusername9', 'testfirstname9', 'testlastname9', 'testemail9@email.com', '$2a$10$el8am2L8Jr83rPDagpPzPOJ3.z5iE9LxaGHLsPzKtpoYk.f8K6DnW',  '2021-08-26', '2021-08-26', 'user', true, 9000.0);
    	('testusername10', 'testfirstname10', 'testlastname10', 'testemail10@email.com', '$2a$10$el8am2L8Jr83rPDagpPzPOJ3.z5iE9LxaGHLsPzKtpoYk.f8K6DnW',  '2021-08-26', '2021-08-26', 'user', true, 10000.0);
commit;
```

-- -----------------------------------------------------
### -- Table `paymybuddy_maindb`.`bank_account`
-- -----------------------------------------------------

```sql
INSERT INTO bank_account (rib, user_id) VALUES
	('fr 1111 1111 1111 1111 A', '1'),
	('fr 2222 2222 2222 2222 A', '2'),
	('fr 3333 3333 3333 3333 A', '3'),
	('fr 4444 4444 4444 4444 A', '1'),
	('fr 5555 5555 5555 5555 A', '5'),
	('fr 6666 6666 6666 6666 A', '1'),
	('fr 7777 7777 7777 7777 A', '7'),
	('fr 8888 8888 8888 8888 A', '1'),
	('fr 9999 9999 9999 9999 A', '9'),
	('fr 1010 1010 1010 1010 A', '10');
commit;
```

-- -----------------------------------------------------
### -- Table `paymybuddy_maindb`.`contact`
-- -----------------------------------------------------

```sql
INSERT INTO 'contact' ('creation_date', 'payer_id', 'contact_id') VALUES
	('2021-08-26', '1', '10'),
	('2021-08-26', '1', '9'),
	('2021-08-26', '1', '8'),
	('2021-08-26', '1', '7'),
	('2021-08-26', '2', '5'),
	('2021-08-26', '2', '4'),
	('2021-08-26', '2', '3'),
	('2021-08-26', '3', '1'),
	('2021-08-26', '3', '1'),
	('2021-08-26', '3', '10');
commit;
```

-- -----------------------------------------------------
### -- Table `paymybuddy_maindb`.`transaction`
-- -----------------------------------------------------

```sql
INSERT INTO 
	transaction (payer_id, beneficiary_id, amount, description, date, commision)
 VALUES
	('1', '2', '100', 'description1', '2021-08-27', 0.5),
	('1', '3', '150', 'description2', '2021-08-27', 0.5),
	('1', '4', '200', 'description3', '2021-08-27', 0.5),
	('2', '1', '100', 'description4', '2021-08-27', 0.5),
	('2', '3', '150', 'description5', '2021-08-27', 0.5),
	('2', '4', '200', 'description6', '2021-08-27', 0.5),
	('3', '1', '150', 'description7', '2021-08-27', 0.5),
	('3', '2', '100', 'description8', '2021-08-27', 0.5),
	('3', '4', '200', 'description1', '2021-08-27', 0.5),
	('9', '2', '100', 'description1', '2021-08-27', 0.5);
commit;
```

-- -----------------------------------------------------
#### -- Table `paymybuddy_maindb`.`transfer`
-- -----------------------------------------------------

```sql
INSERT INTO 'transfer' ('rib', 'date', 'amount', 'type', 'user_id')  VALUES
	('fr 1111 1111 1111 1111 A', '2021-08-27', '100', 'CREDIT', '1');
	('fr 2222 2222 2222 2222 A', '2021-08-27', '200', 'DEBIT', '1');
	('fr 3333 3333 3333 3333 A', '2021-08-27', '130', 'CREDIT', '1');
	('fr 4444 4444 4444 4444 A', '2021-08-27', '140', 'DEBIT', '1');
	('fr 5555 5555 5555 5555 A', '2021-08-27', '150', 'DEBIT', '2');
	('fr 6666 6666 6666 6666 A', '2021-08-27', '160', 'CREDIT', '2');
	('fr 7777 7777 7777 7777 A', '2021-08-27', '170', 'DEBIT', '2');
	('fr 8888 8888 8888 8888 A', '2021-08-27', '180', 'DEBIT', '3');
	('fr 9999 9999 9999 9999 A', '2021-08-27', '190', 'DEBIT', '3');
	('fr 1010 1010 1010 1010 A', '2021-08-27', '100', 'DEBIT', '3');
commit;
```
