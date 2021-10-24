Create TABLE course (
`code` CHAR (3) NOT NULL,
`name` VARCHAR (30) NOT NULL,
`credits` TINYINT NOT NULL,
CONSTRAINT pri_course PRIMARY KEY (code));

CREATE TABLE module (
`code` CHAR (2) NOT NULL,
`name` VARCHAR (30) NOT NULL,
`cost` DECIMAL (8,2) NOT NULL,
`credits` TINYINT NOT NULL,
`course_code` CHAR (3) NOT NULL,
CONSTRAINT pri_module PRIMARY KEY (code),
CONSTRAINT for_module FOREIGN KEY (`code`)
	REFERENCES course (`code`));

CREATE TABLE session (
`code` CHAR (2) NOT NULL,
DOB DATE NOT NULL,
room VARCHAR (30) NULL,
CONSTRAINT pri_session PRIMARY KEY (code),
CONSTRAINT for_session FOREIGN KEY (`code`)
	REFERENCES module (`code`),
Constraint pri_session_two PRIMARY KEY (DOB));

CREATE TABLE take ( 
`no` INT NOT NULL,
`code` CHAR NOT NULL,
grade TINYINT NULL,
CONSTRAINT pri_take PRIMARY KEY (no),
CONSTRAINT for_take FOREIGN KEY (`no`)
	REFERENCES delegate (`no`),
CONSTRAINT pri_take_two PRIMARY KEY (code),
CONSTRAINT for_take FOREIGN KEY (`code`)
	REFERENCES module (`code`));
    
Create TABLE delegate (
`no` INT NOT NULL,
`name` VARCHAR (30) NOT NULL,
phone VARCHAR (30) NULL,
CONSTRAINT pri_delegate PRIMARY KEY (no));    