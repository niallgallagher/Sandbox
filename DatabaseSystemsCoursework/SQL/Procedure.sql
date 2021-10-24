DELIMITER $$ 
 
CREATE procedure generate_new_schedule(OUT schedule_data VARCHAR(50))  -- where is the IN? The question clearly states 'The procedure should accept a course code & start date as arguments'
BEGIN
DECLARE courseinformation CURSOR FOR 
SELECT course_code, `code`, FROM module;

DECLARE start_date VARCHAR(11) DEFAULT '';
DECLARE schedule_data VARCHAR(50);
DECLARE signal_date CONDITION FOR SQLSTATE '01';
IF start_date > current_date + INTERVAL 30 DAY

THEN SIGNAL SQLSTATE '01'
	SET message_warning = 'Incorrect Date'
    
Set schedule_data = '';
OPEN courseinformation;

LOOP

FETch courseinformation INTO schedule_data






END$$
DELIMITER;