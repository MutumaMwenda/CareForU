<?php

$host="localhost";
$db_user="plumvide_care4u";
$db_password="care4u123456789";
$db_name="plumvide_care4u";

$con=mysqli_connect($host, $db_user, $db_password, $db_name);

if($con)
    echo "Connection Success...";
else
    echo "Connection Error...";

?>