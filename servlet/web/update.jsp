<%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2021/12/23
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.beans.*"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%@include file="top.jspf"%>
    <form action="update?flag=update" method="post">
        <input type="hidden" name="pageIndex" value="${param.pageIndex}">
        用户名<input name="userName" value="${user.userName}" /><br/>
        密码<input name="password" value="${user.password}"/><br/>
        <input name="id" type="hidden"  value="${user.id}"/><br/>
        <input type="submit">
    </form>
</body>
</html>
