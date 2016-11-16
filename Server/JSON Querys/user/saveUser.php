<?php

require (dirname(dirname(__FILE__))."/configuration.php");

mysql_connect(DB_HOST,DB_USER,DB_PASSWORD);
mysql_set_charset('utf8');
mysql_select_db(DB_DATABASE);
$q=mysql_query("CALL sp_insert_user('".$_REQUEST['fb_id']."','".$_REQUEST['u_name']."','".$_REQUEST['u_lastname']."','".$_REQUEST['u_email']."')"); 

$rows = array();
while($r = mysql_fetch_assoc($q)) {
    $rows[] = $r;
}
$data = array('SaveUser' => $rows);
print json_encode($data);

 
mysql_close();
?>