<?php

require (dirname(dirname(__FILE__))."/configuration.php");

mysql_connect(DB_HOST,DB_USER,DB_PASSWORD);
mysql_set_charset('utf8');
mysql_select_db(DB_DATABASE);
$q=mysql_query("select count(1) total from RB_USER_RACES a, RB_USER b where a.rbuserid =  b.rbuserid and b.facebookid = ".$_REQUEST['fb_id']." and a.rbraceid = ".$_REQUEST['race_id']); 

$rows = array();
while($r = mysql_fetch_assoc($q)) {
    $rows[] = $r;
}
$data = array('RaceAssistance' => $rows);
print json_encode($data);

 
mysql_close();
?>