<?php
	include 'location.php';   
	require (dirname(dirname(__FILE__))."/configuration.php");
   
	$latitude = $_REQUEST['lat'];
	$longitude = $_REQUEST['long'];
    $location = new LocationManager();
    $country = $location->getCountryByLocation($latitude, $longitude);

	mysql_connect(DB_HOST,DB_USER,DB_PASSWORD);
	mysql_set_charset('utf8');
	mysql_select_db(DB_DATABASE);
	$q=mysql_query("SELECT * FROM  `RB_RACE` WHERE `country_code` = '".$country->short_name."' AND rbcategoryid = ".$_REQUEST['cat_id']." AND status =1 and date >= subdate(NOW(),1) ORDER BY  `date`");
	$rows = array();
	while($r = mysql_fetch_assoc($q)) {
		$rows[] = $r;
	}
	$data = array('Races' => $rows);
	print json_encode($data);

 
mysql_close();
?>