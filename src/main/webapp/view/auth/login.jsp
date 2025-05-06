<%--
  Created by IntelliJ IDEA.
  User: luanpv
  Date: 5/6/25
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/auth/login" method="post">
    Email:
    <input type="email" name="email">
    Pass:
    <input type="password" name="password">
    <button type="submit">Login</button>
</form>
</body>
</html>
