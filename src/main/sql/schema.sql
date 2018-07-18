DELIMITER $$

--创建存储过程
DELIMITER $$

CREATE PROCEDURE execute_seckill(
	IN v_seckill_id INT, IN v_phone VARCHAR(20),
	IN v_kill_time TIMESTAMP, OUT r_result INT)
	
  BEGIN
	DECLARE insert_count INT DEFAULT 0;
	START TRANSACTION;
	INSERT IGNORE INTO success_killed(seckill_id, user_phone, create_time, state)
	    VALUES (v_seckill_id, v_phone, v_kill_time, 0);
	SELECT ROW_COUNT() INTO insert_count;
	IF (insert_count = 0) THEN
	    ROLLBACK;
	    SET r_result = -1;
	ELSEIF (insert_count < 0) THEN
	    ROLLBACK;
	    SET r_result = -2;
	ELSE
	    UPDATE seckill
	    SET number = number - 1
	    WHERE seckill_id = v_seckill_id
		AND start_time < v_kill_time
		AND end_time > v_kill_time
		AND number > 0;
	    SELECT ROW_COUNT() INTO insert_count;
	    IF (insert_count = 0) THEN
	        ROLLBACK;
	        SET r_result = 0;
	    ELSEIF (insert_count < 0) THEN
	        ROLLBACK;
	        SET r_result = -2;
	    ELSE
		COMMIT;
		SET r_result = 1;
	    END IF;
	END IF;
	END$$
DELIMITER;


--测试
SET @r_result = -3;
CALL execute_seckill(1001, 18207136678, NOW(), @r_result);
SELECT @r_result;

	
	
	
	
	
