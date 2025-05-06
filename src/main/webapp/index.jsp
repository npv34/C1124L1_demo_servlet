<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    int a = 5;
%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
    <%= a %>
</h1>
<br/>
<a href="/auth/login">Login</a>
</body>
</html>