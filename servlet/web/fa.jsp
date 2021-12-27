<%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2021/12/25
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="top.jspf"%>
        <form action="faOrder?flag=fafafa" method="post">
            <input type="hidden" name="id" value="${sessionScope.user.id}"/><br>
                 内容 <input type="text" name="msg" /><br>
            <input type="submit" value="提交">
        </form>

</body>
</html>
