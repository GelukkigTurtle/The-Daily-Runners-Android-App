<?php

require (dirname(dirname(__FILE__))."/configuration.php");

mysql_connect(DB_HOST,DB_USER,DB_PASSWORD);
mysql_set_charset('utf8');
mysql_select_db(DB_DATABASE);
$q=mysql_query("CALL sp_set_user_race(".$_REQUEST['fb_id'].",".$_REQUEST['race_id'].",".$_REQUEST['type'].")"); 

$rows = array();
while($r = mysql_fetch_assoc($q)) {
    $rows[] = $r;
}
$data = array('RaceAssistance' => $rows);
print json_encode($data);

 
mysql_close();
?>