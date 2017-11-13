<%--
  Created by IntelliJ IDEA.
  User: LJ0000276
  Date: 2017/6/7
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" autoFlush="false" pageEncoding="UTF-8" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<h1>登录</h1>
<div class="login-contain">
    <span>账号登录</span>
    <form action="login.do" method="post" name="loginForm" >
        <div>用户:<input type="text" name="username" placeholder="请输入用户名"/></div>
        <div>密码:<input type="password" name="password" placeholder="请输入密码"/></div>
        <span> <input type="submit" name="submit" content="提交" value="提交" ></span>
    </form>
</div>
</body>
</html>
