<%--
  Created by IntelliJ IDEA.
  User: luanpv
  Date: 5/6/25
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/users/create" method="post">
    Name:
    <input type="text" name="name">
    Email:
    <input type="email" name="email">
    Phone:
    <input type="text" name="phone">
    Address:
    <input type="text" name="address">
    Password:
    <input type="password" name="password">
    <button type="submit">Save</button>
</form>
</body>
</html>
