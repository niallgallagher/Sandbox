CREATE TRIGGER trig_audit
AFTER UPDATE ON gradeaudit FOR EACH ROW
BEGIN
  SET `date` = GETDATE()
  SET `oldgrade` = take.`grade`
    SELECT  take.,
        INSERTED.TextColumn,
        INSERTED.ValueColumn,
        INSERTED.NumberColumn,
        'Update' AS ActionType
FROM    INSERTED
        INNER JOIN

UPDATE `date` SET `date` = CURRENT_TIMESTAMP;




