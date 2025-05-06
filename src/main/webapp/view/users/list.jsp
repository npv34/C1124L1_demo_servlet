<%@ page import="java.util.List" %>
<%@ page import="org.app.webapp.entities.User" %><%--
  Created by IntelliJ IDEA.
  User: luanpv
  Date: 5/6/25
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<User> listUser = (List<User>) request.getAttribute("listUser");
    int total = listUser.size();
    int stt = 1;
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>User List</h1>
<p>Total: <%= total%> users</p>
<a href="/users/create">Create </a>
<form action="/users/search" method="get">
    <input type="text" name="keyword">
    <button type="submit">Search</button>
</form>
<table>
    <tr>
        <td>STT</td>
        <td>Name</td>
        <td>Email</td>
        <td></td>
    </tr>
    <% for (User item: listUser) { %>
        <tr>
            <td><%= stt %></td>
            <td><%= item.getName() %></td>
            <td><%= item.getEmail() %></td>
            <td>
                <a href="/users/delete?id=<%= item.getId()%>">Delete</a>
                <a href="/users/update?id=<%= item.getId()%>">Update</a>
            </td>
        </tr>
        <% stt++; %>
    <% } %>
</table>
</body>
</html>
