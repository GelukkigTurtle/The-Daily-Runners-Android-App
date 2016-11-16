--para el calendario
select a.name , a.date from RB_RACE a, RB_USER b, RB_USER_RACES c where a.rbraceid = c.rbraceid and c.rbuserid = b.rbuserid and  b.facebookid = 12345;

--obtener si asistira a la carrera
select count(1) total from RB_USER_RACES a, RB_USER b where a.rbuserid =  b.rbuserid and b.facebookid = 12345 and a.rbraceid = 3;