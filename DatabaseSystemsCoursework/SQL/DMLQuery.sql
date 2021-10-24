SELECT `code`,`name`,`credits`
FROM module;

SELECT `no`,`name`
FROM delegate
ORDER BY `name` DESC;

SELECT `code`,`name`,`credits`
FROM course
WHERE `name` LIKE 'Network%';

SELECT MAX(`grade`)
from take;

SELECT `no`
FROM take
WHERE `grade` =
(SELECT Max(`grade`) FROM take);

SELECT `name`,`no`
FROM delegate
WHERE `no` = (
SELECT `no`
FROM take
WHERE `grade` =
(SELECT Max(`grade`) FROM take));

SELECT `code`,`date`
FROM sessions
WHERE `date` BETWEEN 2022 AND `room` IS NULL;

SELECT delegate.`no`, delegate.`name`, take.`code`
FROM delegate INNER JOIN take
ON take.`no` = delegate.`no`
WHERE grade < 40;

SELECT delegate.`name`, delegate.`no`
FROM delegate INNER join take
ON take.`no` = delegate.`no`
WHERE `grade` =
(SELECT Max(`grade`) FROM take);

SELECT delegate.`no`, delegate.`name`, 
course.`code`, course.`name`, course.credits, 
SUM(module.credits) AS ` Module Credits Attained`
FROM delegate INNER JOIN take
ON take.`no` = delegate.`no`
INNER JOIN module ON take.`code` = module.`code`
INNER JOIN course ON module.course_code = course.`code`
WHERE grade > 40
GROUP BY delegate.`no`;

SELECT delegate.`no`, delegate.`name`, 
course.`code`, course.`name`, course.credits, 
SUM(module.credits) AS ` Module Credits Attained`
FROM delegate INNER JOIN take
ON take.`no` = delegate.`no`
INNER JOIN module ON take.`code` = module.`code`
INNER JOIN course ON module.course_code = course.`code`
WHERE grade > 40
GROUP BY delegate.`no`
HAVING SUM(module.credits) = course.credits;


