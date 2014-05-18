<?php

if(isset($_REQUEST['q'])){
$q=$_REQUEST['q'];
$q='{"menu": {
  "id": "file",
  "value": "File",
  "popup": {
    "menuitem": [
      {"value": "New", "onclick": "CreateNewDoc()"},
      {"value": "Open", "onclick": "OpenDoc()"},
      {"value": "Close", "onclick": "CloseDoc()"}
    ]
  }
}}';

$q=str_replace(' ', '', $q);
$q=preg_replace('/\s+/', '', $q);

echo "<img src=https://chart.googleapis.com/chart?chs=300x300&cht=qr&chl=".$q."&choe=UTF-8%22%20/>";

}


?>