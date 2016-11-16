USE `justrunn_app`;
DROP procedure IF EXISTS `sp_insert_user`;

DELIMITER $$
USE `justrunn_app`$$
CREATE PROCEDURE `sp_insert_user` (IN p_idfbuser VARCHAR(1000), IN p_name VARCHAR(500), IN p_lastname VARCHAR(500), IN p_email VARCHAR(100))
BEGIN
DECLARE total_user INT DEFAULT 0;
DECLARE v_mail_user INT DEFAULT 0;

select count(1) into total_user from RB_USER where facebookid = p_idfbuser;

IF total_user < 1 THEN
	select count(1) into v_mail_user from RB_USER where email = p_email;
	IF v_mail_user >= 1 THEN
		delete from RB_USER where email = p_email;
	END IF;
	insert into RB_USER (first_name,last_name,email,facebookid,account_status,date_created) values (p_name,p_lastname,p_email,p_idfbuser,1,NOW());
END IF;

END$$

DELIMITER ;

