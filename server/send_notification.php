<?php

require "init.php";

$message=$_POST['message'];
$title=$_POST['title'];
$app_path='https://fcm.googleapis.com/fcm/send';
$server_key='AAAAcId07Mo:APA91bGop3p40zTvn1VuQuTKj3p7uIb0ErZGvwSzat-zZaQkc_Lkvq5zA4tq_3yrU5AIUgEtdUCKh5-Ylex_4uXCcVUhmFmxk4FHtg2xzL8_olWzvMRzQWWQMrC7elzg9Cn5A-zJGo7j';

$sql="select token from care4u_info";
$result=mysqli_query($con, $sql);
$row=mysqli_fetch_row($result);
$key=$row[0];

$headers=array(
            'Authorization:key=' .$server_key,
            'Content-Type:application/json'
            );

$fields=array('to'=>$key,
                'notification'=>array('title'=>$title, 'body'=>$message));

$payload=json_encode($fields);

$curl_session=curl_init();
curl_setopt($curl_session, CURLOPT_URL, $app_path);
curl_setopt($curl_session, CURLOPT_POST, true);
curl_setopt($curl_session, CURLOPT_HTTPHEADER, $headers);
curl_setopt($curl_session, CURLOPT_RETURNTRANSFER, true);
curl_setopt($curl_session, CURLOPT_SSL_VERIFYPEER, false);
curl_setopt($curl_session, CURLOPT_IPRESOLVE, CURLOPT_IPRESOLVE_V4);
curl_setopt($curl_session, CURLOPT_POSTFIELDS, $payload);

$result=curl_exec($curl_session);

curl_close($curl_session);
mysqli_close($con);

?>