<%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2021/12/25
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<style>

    table {

        border: 1px solid red;
        margin: 0 auto;

    }
    th{
        width:200px;
        border: 1px solid black;
        border-collapse: collapse;
    }
    tr,td{
        width:200px;
        border: 1px solid black;
        border-collapse: collapse;
    }
</style>
<body>
<%@include file="top.jspf"%>
        <table >
            <tr>
                <th>编号</th> <th>内容</th> <th>发布时间</th> <th>发布者</th> <th>操作</th>
            </tr>
            <c:if test="${orderList.size()>0}">

                <c:forEach items="${orderList}" var="o" varStatus="s">
                    <tr>
                    <td>${s.count}</td> <td>${o.msg}</td> <td>${o.createTime}</td> <td>${o.fuid}</td> <td><a href="qOrder?flag=qqq&id=${o.id}">抢单</a></td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${orderList.size()==0}">
                暂时无单
            </c:if>
            <label style="color:red"> ${msg}</label>

        </table>
</body>
</html>
