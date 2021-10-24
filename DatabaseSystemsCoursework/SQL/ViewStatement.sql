ALTER TABLE sessions
ALTER `date` SET DEFAULT (CURRENT_TIMESTAMP);

 DROP VIEW IF EXISTS sessions_future;
 
CREATE VIEW sessions_future
AS
SELECT *
FROM sessions
WHERE `date` > CURRENT_TIMESTAMP
WITH CHECK OPTION;

INSERT INTO sessions_future (`code`, `date`, `room`) VALUES ('A2','2020.10.5',201);