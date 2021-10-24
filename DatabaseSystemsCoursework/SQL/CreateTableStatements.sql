drop table if exists take;
drop table if exists sessions;
drop table if exists module;
drop table if exists delegate;
drop table if exists course;
drop table if exists gradeaudit;


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