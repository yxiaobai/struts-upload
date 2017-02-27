<%@ page language="java" pageEncoding="UTF-8"  %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <title>文件上传</title>
    <style>
        input{margin: 5px;padding: 5px;}
    </style>
</head>
<html>
<body>
<h3>单文件上传</h3>
<form action="upload.action" method="post" enctype="multipart/form-data">
 名字：   <input type="text" name="author"><br>
 文件：   <input type="file" name="face"><br>
    <input type="submit" value="提交上传">
</form>
<hr>
<h3>多文件上传</h3>
<form action="uploads.action" method="post" enctype="multipart/form-data">
    名字：   <input type="text" name="author"><br>
    文件：   <input type="file" name="face" multiple><br>
    <input type="submit" value="多文件提交上传">
</form>
<hr>
<a href="down.action?filename=a.jpg" download>下载</a>
</body>
</html>
