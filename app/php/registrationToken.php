<?php
 $host = "localhost";
 $db = "esercizio";
 $token=$_POST["token"];
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
    $sql="INSERT INTO registration_device(token) VALUES('$token')";
    $statment= $conn->prepare($sql);
    $statment->execute();
}catch(PDOException $e){
    echo $e->getMessage();
}
?>

