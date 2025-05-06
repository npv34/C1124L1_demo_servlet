<%@ page import="org.app.webapp.entities.User" %><%--
  Created by IntelliJ IDEA.
  User: luanpv
  Date: 5/6/25
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) request.getAttribute("userUpdate");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/users/update?id=<%= user.getId() %>" method="post">
    Name:
    <input type="text" name="name" value="<%= user.getName() %>">
    Email:
    <input type="email" value="<%= user.getEmail() %>" disabled>
    <button type="submit">Update</button>
</form>
</body>
</html>
