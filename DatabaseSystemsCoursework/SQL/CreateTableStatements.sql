drop table if exists take;
drop table if exists sessions;
drop table if exists module;
drop table if exists delegate;
drop table if exists course;
drop table if exists gradeaudit;

drop table if exists employee_iq;
drop table if exists audit1;


Create TABLE course (
`code` CHAR (3) NOT NULL,
`name` VARCHAR (30) NOT NULL,
`credits` TINYINT NOT NULL  DEFAULT(50),
CONSTRAINT pk_course PRIMARY KEY (`code`)
);

CREATE TABLE module (
`code` CHAR (2) NOT NULL,
`name` VARCHAR (30) NOT NULL,
`cost` DECIMAL (8,2) NOT NULL,
`credits` TINYINT NOT NULL  DEFAULT(25),
`course_code` CHAR (3) NOT NULL,
CONSTRAINT pk_module PRIMARY KEY (`code`),
CONSTRAINT fkmodule_course FOREIGN KEY (`course_code`)
	REFERENCES course (`code`)
);

Create TABLE delegate (
`no` INT NOT NULL,
`name` VARCHAR (30) NOT NULL,
`phone` VARCHAR (30) NULL,
CONSTRAINT pk_delegate PRIMARY KEY (`no`)
);  

CREATE TABLE take ( 
`no` INT NOT NULL,
`code` CHAR (2) NOT NULL,
`grade` TINYINT NULL,
CONSTRAINT pk_take PRIMARY KEY (`no`, `code`),
CONSTRAINT fktake_delegate FOREIGN KEY (`no`)
	REFERENCES delegate (`no`) ,
CONSTRAINT fktake_module FOREIGN KEY (`code`)
    REFERENCES module (`code`)
); 

CREATE TABLE sessions (
`code` CHAR (2) NOT NULL,
`date` DATE NOT NULL,
`room` VARCHAR (30) NULL,
CONSTRAINT pk_sessions PRIMARY KEY (`code`, `date`),
CONSTRAINT fksessions_module FOREIGN KEY (`code`)
	REFERENCES module (`code`)
);

CREATE TABLE gradeaudit (
`username` VARCHAR (30) NOT NULL,
`date` DATE NOT NULL,
`oldgrade` TINYINT NOT NULL,
`newgrade` TINYINT NOT NULL
);

CREATE TABLE `employee_iq` (
  `id` INT AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `dob` DATETIME NOT NULL,
  `iq` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
  
INSERT INTO `employee_iq`
(`username`,
`dob`,
`iq`)
VALUES
('walldavid',
'1977-10-07 02:05:55',
140);

  
CREATE TABLE `audit1` (
`username` VARCHAR(45) NOT NULL,
`action` VARCHAR(8) NOT NULL,
`changedate` DATETIME NOT NULL,
`oldIq` INT NOT NULL,
`newIq` INT NOT NULL);
  
CREATE TRIGGER `on_iq_change`
	AFTER UPDATE ON `employee_iq`
    FOR EACH ROW
INSERT INTO `audit1`
SET `action` = 'update',
	`username` = 'walldavid',
    `changedate` = NOW(),
    `oldIq` = OLD.iq,
    `newIq` = NEW.iq;

UPDATE `employee_iq`
	SET `iq` = 145
    WHERE `username` = 'walldavid';