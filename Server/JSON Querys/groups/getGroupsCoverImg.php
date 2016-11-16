<?php

require (dirname(dirname(__FILE__))."/configuration.php");

mysql_connect(DB_HOST,DB_USER,DB_PASSWORD);
mysql_set_charset('utf8');
mysql_select_db(DB_DATABASE);
$q=mysql_query("SELECT * FROM  `RB_PHOTO_GROUP` WHERE  `rbgroupid` = ".$_REQUEST['group_id']);
$rows = array();
while($r = mysql_fetch_assoc($q)) {
    $rows[] = $r;
}
$data = array('GroupImages' => $rows);
print json_encode($data);

 
mysql_close();
?>