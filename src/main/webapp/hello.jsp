<%--
  Created by IntelliJ IDEA.
  User: luanpv
  Date: 5/6/25
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String message = (String) request.getAttribute("message");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1><%= message %></h1>
<a href="/users">user manager</a>
</body>
</html>
