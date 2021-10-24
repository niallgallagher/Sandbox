DELIMITER $$

drop procedure if exists generate_new_schedule $$
CREATE Procedure generate_new_schedule(IN start_date DATETIME, IN course_code CHAR(3))
BEGIN
SELECT code
from module
WHERE module.`course_code` = course_code;

#IF start_date > current_date + INTERVAL 30 DAY THEN
	#SIGNAL SQLSTATE '01';
	#SET message_warning = 'Incorrect Date';
#END IF;
    
END$$

CALL generate_new_schedule('01.05.2023','WSD');

# Select all module codes from the module table based on the course code used in the input to the SP
# Create a CURSOR that allows you to interate through that result set
# For each module code you need to check if the date to be entered is a weekday and if so, insert a new row in to sessions