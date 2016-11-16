<?php

    include 'location.php';    
    $ip =  $_SERVER['REMOTE_ADDR'];
	$latitude =$_REQUEST['lat'];
	$longitude =$_REQUEST['long'];
    $location = new LocationManager();
     
	 
    $rows = array();
    $country = $location->getCountryByLocation($latitude, $longitude);
	$rows[] = $country;
    $data = array('Location' => $rows);
    print json_encode($data);    
	

?>