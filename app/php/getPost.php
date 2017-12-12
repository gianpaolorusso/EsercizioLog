<?php
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
$array=[];
    $sql="SELECT * FROM post WHERE id='$idPost'";
    foreach($conn->query($sql) as $row)
        {
         $obj = (object) array(
             "nome"=>$row["nome"],
             "id"=>$row["id"],
             "giorno"=>$row["giorno"],
             "titolo"=>$row["titolo"]
             );
            array_push($array,$obj);
        }
 echo json_encode($array);

?>

