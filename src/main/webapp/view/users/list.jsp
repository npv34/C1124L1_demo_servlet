<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var="listUser" value="${requestScope['listUser']}"/>
<c:set var="keyword" value="${requestScope['keyword']}"/>
<c:set var="total" value="${listUser.size()}"/>
<c:set var="stt" value="1"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/statics/css/style.css">
</head>
<body>
<h1>User List</h1>
<p>Total: <c:out value="${total}"/> users</p>
<a href="/users/create">Create </a>
<form action="/users/search" method="get">
    <input type="text" value="${keyword}" name="keyword">
    <button type="submit">Search</button>
</form>
<table id="table-user">
    <tr>
        <td>STT</td>
        <td>Name</td>
        <td>Email</td>
        <td>Status</td>
        <td></td>
    </tr>
    <c:forEach items="${listUser}" var="item">
        <tr>
            <td><c:out value="${stt}"/></td>
            <td><c:out value="${item.getName()}"/></td>
            <td><c:out value="${item.getEmail()}"/></td>
            <td>
                <c:if test="${item.getActive()}">
                    <c:out value="Active"/>
                </c:if>
                <c:if test="${!item.getActive()}">
                    <c:out value="De-Active"/>
                </c:if>
            </td>
            <td>
                <a onclick="return confirm('Are you sure?')" href="/users/delete?id=<c:out value="${item.getId()}" />">Delete</a>
                <a href="/users/update?id=<c:out value="${item.getId()}" />">Update</a>
            </td>
        </tr>
        <c:set var="stt" value="${stt + 1}"/>
    </c:forEach>
</table>
</body>
</html>
