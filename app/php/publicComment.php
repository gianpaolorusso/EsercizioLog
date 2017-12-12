<?php
 $host = "localhost";
 $db = "esercizio";
 $data=$_POST["data"];
 $user=$_POST["nome"];
 $testo=$_POST["testo"];
 $idpost=$_POST["idpost"];
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
 
try{
    $sql="INSERT INTO commenti_post(testo,giorno,nome_user,id_post) VALUES('$testo','$data','$user','$idpost')";
    $statment= $conn->prepare($sql);
    $statment->execute();
}catch(PDOException $e){
    echo $e->getMessage();
}

define( 'API_ACCESS_KEY', 'AAAA_UdjhB8:APA91bFZj39YCthUwefAP3vIC5tsCI9HRy7PXYaLuPIXO8T-9Sw2BhW5RUCEbVPBKvqLGjsOG4hCiyuGJC3JH8CDDfXpqjUFhJ-ec9cHDPmE_9-YgprRIV3aBHjkErJn2XMGBEtx6AVb' );
$host = "localhost";
$db = "esercizio";
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
$index=0;
    $sql="SELECT * FROM registration_device";
    foreach($conn->query($sql) as $row){
        $registrationIDs[$index]=$row["token"];
        $index++;
        }
$messaggio=$user." ha commentato il post ".$idpost."\n".$testo;
$fcmMsg = array(
	'body' => $messaggio,
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
    'registration_ids'  => $registrationIDs,
    'data'              => $fcmMsg
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

