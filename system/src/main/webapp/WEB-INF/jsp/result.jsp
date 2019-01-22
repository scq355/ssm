<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <script src="../../js/jquery.min.js" type="text/javascript"></script>
    <script src="../../js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
<div class="panel panel-default">
    <div class="panel-heading">Panel heading</div>
    <div class="panel-body">
        <p>用户个人信息</p>
    </div>
    <table class="table table-condensed table-hover">
        <thead>
            <tr>
                <th>id</th>
                <th>姓名</th>
                <th>昵称</th>
                <th>电话</th>
                <th>邮箱</th>
                <th>注册时间</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${user.id}</td>
                <td>${user.userName}</td>
                <td>${user.nickName}</td>
                <td>${user.phoneNumber}</td>
                <td>${user.email}</td>
                <td>${user.registerTime}</td>
            </tr>
        </tbody>
    </table>
</div>
</body>
</html>
