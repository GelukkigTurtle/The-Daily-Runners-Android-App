USE `justrunn_app`;
DROP procedure IF EXISTS `sp_set_user_race`;

DELIMITER $$
USE `justrunn_app`$$
CREATE PROCEDURE `sp_set_user_race` (IN p_userfbid LONG,IN p_race_id LONG, IN p_type INT)
BEGIN
DECLARE v_races_count INT DEFAULT 0;
DECLARE v_user_id LONG DEFAULT 0;
DECLARE v_going LONG DEFAULT 0;

select  rbuserid  into v_user_id from  RB_USER where facebookid = p_userfbid;

-- Goin
IF p_type = 1 THEN 
select count(1) into v_races_count from RB_USER_RACES where rbuserid = v_user_id and rbraceid = p_race_id;
 IF v_races_count < 1 THEN
  insert into RB_USER_RACES  values (v_user_id,p_race_id);
  SELECT COUNT(1) into v_going FROM RB_USER_RACES WHERE rbraceid = p_race_id;
  UPDATE RB_RACE set going = v_going, date_modified = NOW() where rbraceid = p_race_id;

 END IF;

ELSEIF p_type = 2 THEN 
-- Not Goin
select count(1) into v_races_count from RB_USER_RACES where rbuserid = v_user_id and rbraceid = p_race_id;
 IF v_races_count >= 0 THEN
  delete from RB_USER_RACES where rbuserid = v_user_id and rbraceid = p_race_id;
  SELECT COUNT(1) into v_going FROM RB_USER_RACES WHERE rbraceid = p_race_id;
  UPDATE RB_RACE set going = v_going, date_modified = NOW() where rbraceid = p_race_id;
 END IF;

END IF;
END  $$

DELIMITER ;