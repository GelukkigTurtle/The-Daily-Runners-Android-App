<?php
  $headers = array(
        'Authorization: key=' . 'AIzaSyDX8K55nz0YSzWDKeO1TDGe10j-L_Lw9Fg', // HERE IT IS DEMO KEY
        'Content-Type: application/json'
    );
// response json
$json = array();
 
/**
 * Registering a user device
 * Store reg id in users table
 */
if (isset($_POST["name"]) && isset($_POST["email"]) && isset($_POST["regId"])) {
    $name = $_POST["name"];
    $email = $_POST["email"];
    $gcm_regid = $_POST["regId"]; // GCM Registration ID
    // Store user details in db
    include_once './db_functions.php';
    include_once './GCM.php';
 
    $db = new DB_Functions();
    $gcm = new GCM();
 
    $res = $db->storeUser($name, $email, $gcm_regid);
 
   //$registatoin_ids = array($gcm_regid);
   // $message = array("price" => "Welcome to Just Runners!");
 
    //$result = $gcm->send_notification($registatoin_ids, $message);
 
   // echo $result;
} else {
    // user details missing
}
?>