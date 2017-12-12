<?php
 $host = "localhost";
 $db = "esercizio";
 $userLog=$_POST["username"];
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
    $sql="SELECT comunity.nome,comunity.id,appartenenza.idcomunity FROM comunity,appartenenza WHERE appartenenza.username='$userLog' AND comunity.id=appartenenza.idcomunity";
    foreach($conn->query($sql) as $row)
        {
         $obj = (object) array(
             "nome"=>$row["nome"],
             "id"=>$row["idcomunity"]
         );
           array_push($array,$obj);
}

 echo json_encode($array);

?>

