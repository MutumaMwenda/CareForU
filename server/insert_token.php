<?php

require "init.php";
$token = $_POST["app_token"];

$sql = "insert into care4u_info values('".$token."');";

mysqli_query($con,$sql);
mysqli_close($con);

?>