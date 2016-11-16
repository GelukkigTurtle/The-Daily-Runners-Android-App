<?php

require (dirname(dirname(__FILE__))."/configuration.php");

mysql_connect(DB_HOST,DB_USER,DB_PASSWORD);
mysql_set_charset('utf8');
mysql_select_db(DB_DATABASE);

$q=mysql_query("SELECT * FROM RB_RACE where status =1 and date >= subdate(NOW(),1) ORDER BY  `date` ");

$rows = array();
while($r = mysql_fetch_assoc($q)) {
    $rows[] = $r;
}
$data = array('Races' => $rows);
print json_encode($data);

 
mysql_close();
?>