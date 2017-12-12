<?php
define( 'API_ACCESS_KEY', 'AIzaSyBTcET9FT-daxwhP52vLkHGs8Yq2uJO53s' );

$host = "localhost";
 $db = "esercizio";
 $idPost=$_POST["idpost"];
 $username ="root";


try{
    $conn = new PDO("mysql:host=$host;dbname=$db;charset=utf8",$username);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $conn->setAttribute(PDO::MYSQL_ATTR_INIT_COMMAND,"SET NAMES utf8");
}
catch(PDOException $e)
{
echo $e->getMessage();
}
$registrationIDs=[];

    $sql="SELECT token FROM registration_device";
    foreach($conn->query($sql) as $row){
        $registrationIDs.push($row["token"]);
        }

$fcmMsg = array(
	'body' => 'qualcuno ha commentato un post',
	'title' => 'Notifica commento',
	'sound' => "default"
);
// I haven't figured 'color' out yet.  
// On one phone 'color' was the background color behind the actual app icon.  (ie Samsung Galaxy S5)
// On another phone, it was the color of the app icon. (ie: LG K20 Plush)

// 'to' => $singleID ;  // expecting a single ID
// 'registration_ids' => $registrationIDs ;  // expects an array of ids
// 'priority' => 'high' ; // options are normal and high, if not set, defaults to high.
$fcmFields = array(
	'to' => $registrationIDs,
        'priority' => 'high',
	'notification' => $fcmMsg
);

$headers = array(
	'Authorization: key=' . API_ACCESS_KEY,
	'Content-Type: application/json'
);
 
$ch = curl_init();
curl_setopt( $ch,CURLOPT_URL, 'https://fcm.googleapis.com/fcm/send' );
curl_setopt( $ch,CURLOPT_POST, true );
curl_setopt( $ch,CURLOPT_HTTPHEADER, $headers );
curl_setopt( $ch,CURLOPT_RETURNTRANSFER, true );
curl_setopt( $ch,CURLOPT_SSL_VERIFYPEER, false );
curl_setopt( $ch,CURLOPT_POSTFIELDS, json_encode( $fcmFields ) );
$result = curl_exec($ch );
curl_close( $ch );
echo $result . "\n\n";
?>