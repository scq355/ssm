<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户信息</title>
    <link rel="shortcut icon" href="../../images/icon.png">
    <link href="../../css/bootstrap.css" rel="stylesheet">
    <script src="../../js/jquery.min.js" type="text/javascript"></script>
    <script src="../../js/bootstrap.js" type="text/javascript"></script>
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
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.userName}</td>
                    <td>${user.nickName}</td>
                    <td>${user.phoneNumber}</td>
                    <td>${user.email}</td>
                    <td>${user.registerTime.toLocaleString()}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div class="right">
    <p>用户数量：${userNum}</p>
</div>
</body>
</html>
