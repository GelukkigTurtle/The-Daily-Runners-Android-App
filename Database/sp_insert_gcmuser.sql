USE `justrunn_app`;
DROP procedure IF EXISTS `sp_insert_gcmuser`;

DELIMITER $$
USE `justrunn_app`$$
CREATE PROCEDURE `sp_insert_gcmuser` (IN p_name VARCHAR(1000), IN p_email VARCHAR(500), IN p_gcm_regid TEXT)
BEGIN
DECLARE total_user INT DEFAULT 0;


select count(1) into total_user from RB_GCM_USERS where email = p_email;

IF total_user >= 1 THEN
delete from RB_GCM_USERS where email = p_email;
END IF;

INSERT INTO RB_GCM_USERS(name, email, gcm_regid, created_at) VALUES(p_name, p_email, p_gcm_regid, NOW());


END$$

DELIMITER ;

