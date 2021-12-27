
<%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2021/12/23
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList,com.beans.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>



</head>
<body>
<%@include file="top.jspf"%>

<a href="searchAllUser?flag=allUser&pageIndex=1">查询所有用户</a><br/>
<a href="faOrder?flag=faOrder">发单</a><br/>
<a href="qOrder?flag=qOrder">抢单</a><br/>
<label style="color:red"> ${msg}</label>
</body>
</html>
