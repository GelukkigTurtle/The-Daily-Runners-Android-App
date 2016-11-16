<?php

require (dirname(dirname(__FILE__))."/configuration.php");

mysql_connect(DB_HOST,DB_USER,DB_PASSWORD);
mysql_set_charset('utf8');
mysql_select_db(DB_DATABASE);
$q=mysql_query("select a.* from RB_RACE a, RB_USER b, RB_USER_RACES c where a.rbraceid = c.rbraceid and c.rbuserid = b.rbuserid and  b.facebookid = ".$_REQUEST['fb_id']); 

$rows = array();
while($r = mysql_fetch_assoc($q)) {
    $rows[] = $r;
}
$data = array('MyCalendar' => $rows);
print json_encode($data);

 
mysql_close();
?>