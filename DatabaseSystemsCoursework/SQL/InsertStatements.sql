INSERT INTO course (`code`, `name`, `credits`) VALUES ('WSD', 'Web Systems Development', 75);
INSERT INTO course (`code`, `name`, `credits`) VALUES ('DDM', 'Database Design & Management', 100);
INSERT INTO course (`code`, `name`, `credits`) VALUES ('NSF', 'Network Security & Forensics', 75);

INSERT INTO module (`code`, `name`, `cost`, `credits`, `course_code`) VALUES ('A2', 'ASP.NET', 250.00, 25, 'WSD');
INSERT INTO module (`code`, `name`, `cost`, `credits`, `course_code`) VALUES ('A3', 'PHP', 250.00, 25, 'WSD');
INSERT INTO module (`code`, `name`, `cost`, `credits`, `course_code`) VALUES ('A4', 'JavaFX', 350.00, 25, 'WSD');
INSERT INTO module (`code`, `name`, `cost`, `credits`, `course_code`) VALUES ('B2', 'Oracle', 750.00, 50, 'DDM');
INSERT INTO module (`code`, `name`, `cost`, `credits`, `course_code`) VALUES ('B3', 'SQLS', 750.00, 50, 'DDM');
INSERT INTO module (`code`, `name`, `cost`, `credits`, `course_code`) VALUES ('C2', 'Law', 250.00, 25, 'NSF');
INSERT INTO module (`code`, `name`, `cost`, `credits`, `course_code`) VALUES ('C3', 'Forensics', 350.00, 25, 'NSF');
INSERT INTO module (`code`, `name`, `cost`, `credits`, `course_code`) VALUES ('C4', 'Networks', 250.00, 25, 'NSF');

INSERT INTO delegate (`no`, `name`, `phone`) VALUES (2001, 'Mike', NULL);
INSERT INTO delegate (`no`, `name`, `phone`) VALUES (2002, 'Andy', NULL);
INSERT INTO delegate (`no`, `name`, `phone`) VALUES (2003, 'Sarah', NULL);
INSERT INTO delegate (`no`, `name`, `phone`) VALUES (2004, 'Karen', NULL);
INSERT INTO delegate (`no`, `name`, `phone`) VALUES (2005, 'Lucy', NULL);
INSERT INTO delegate (`no`, `name`, `phone`) VALUES (2006, 'Steve', NULL);
INSERT INTO delegate (`no`, `name`, `phone`) VALUES (2007, 'Jenny', NULL);
INSERT INTO delegate (`no`, `name`, `phone`) VALUES (2008, 'Tom', NULL);

INSERT INTO take (`no`, `code`, `grade`)  VALUES (2003, 'A2', 68);
INSERT INTO take (`no`, `code`, `grade`)  VALUES (2003, 'A3', 72);
INSERT INTO take (`no`, `code`, `grade`)  VALUES (2003, 'A4', 53);
INSERT INTO take (`no`, `code`, `grade`)  VALUES (2005, 'A2', 48);
INSERT INTO take (`no`, `code`, `grade`)  VALUES (2005, 'A3', 52);
INSERT INTO take (`no`, `code`, `grade`)  VALUES (2002, 'A2', 20);
INSERT INTO take (`no`, `code`, `grade`)  VALUES (2002, 'A3', 30);
INSERT INTO take (`no`, `code`, `grade`)  VALUES (2002, 'A4', 50);
INSERT INTO take (`no`, `code`, `grade`)  VALUES (2008, 'B2', 90);
INSERT INTO take (`no`, `code`, `grade`)  VALUES (2007, 'B2', 73);
INSERT INTO take (`no`, `code`, `grade`)  VALUES (2007, 'B3', 63);

INSERT INTO sessions (`code`, `date`, `room`) VALUES ('A2', '2021.06.05', '305');
INSERT INTO sessions (`code`, `date`, `room`) VALUES ('A3', '2021.06.06', '307');
INSERT INTO sessions (`code`, `date`, `room`) VALUES ('A4', '2021.06.07', '305');
INSERT INTO sessions (`code`, `date`, `room`) VALUES ('B2', '2021.08.22', '208');
INSERT INTO sessions (`code`, `date`, `room`) VALUES ('B3', '2021.08.23', '208');
INSERT INTO sessions (`code`, `date`, `room`) VALUES ('A2', '2022.05.01', '303');
INSERT INTO sessions (`code`, `date`, `room`) VALUES ('A3', '2022.05.02', '305');
INSERT INTO sessions (`code`, `date`, `room`) VALUES ('A4', '2022.05.03', '303');
INSERT INTO sessions (`code`, `date`,`room`) VALUES ('B2', '2022.07.10', NULL);
INSERT INTO sessions (`code`, `date`,`room`) VALUES ('B3', '2022.07.11', NULL);