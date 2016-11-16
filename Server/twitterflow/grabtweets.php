<?php
header ('Access-Control-Allow-Origin: *');
header('Content-type: application/json');
if (isset($_SESSION['LAST_ACTIVITY']) && (time() - $_SESSION['LAST_ACTIVITY'] > 1800)) {
    // last request was more than 30 minutes ago
    session_unset();     // unset $_SESSION variable for the run-time 
    session_destroy();   // destroy session data in storage
}
$_SESSION['LAST_ACTIVITY'] = time(); // update last activity time stamp
//We use already made Twitter OAuth library
//https://github.com/mynetx/codebird-php
require_once ('codebird.php');

//Twitter OAuth Settings, enter your settings here:
$CONSUMER_KEY = 'iV6xA0WYyCunOejAplsPUw';
$CONSUMER_SECRET = 'Hr36YZl9Hrfh2L4CBpscQn17GnLjfzHVysyXxm1gSE';
$ACCESS_TOKEN = '363964160-YlV6GGGHyUakaSaE6S1KSnz8fjl0aqQlcXcXBbpm';
$ACCESS_TOKEN_SECRET = 'te5ofXRgOiRTT9ESHgIMQSZAZc6WZ2vIgVouJ0vYfmHRJ';

//Get authenticated
Codebird::setConsumerKey($CONSUMER_KEY, $CONSUMER_SECRET);
$cb = Codebird::getInstance();
$cb->setToken($ACCESS_TOKEN, $ACCESS_TOKEN_SECRET);


//retrieve posts
$q = $_POST['q'];
$count = $_POST['count'];
$api = $_POST['api'];

//https://dev.twitter.com/docs/api/1.1/get/statuses/user_timeline
//https://dev.twitter.com/docs/api/1.1/get/search/tweets
$params = array(
	'screen_name' => $q,
	'q' => $q,
	'count' => $count
);

//Make the REST call
$data = (array) $cb->$api($params);

//Output result in JSON, getting it ready for jQuery to process
echo json_encode($data);

?>