
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
    <script>
        window.onload=function(){
            sgArr=document.getElementsByName("sg");
            for(var i=0;i<sgArr.length;i++){

                sgArr[i].onclick=t1;
            }
        }
        function t1(){
            var arr= document.getElementsByName("duo");
            var result;
            for (var i=0;i<arr.length;i++){
                if (arr[i].checked){
                    result+="true";
                }else{
                    result+="false";
                }
            }
            var all=document.getElementById("all");
            if (result.indexOf("false")!=-1){
                all.checked="";
            }else{
                all.checked="checked";
            }
        }
        function t2() {
            var all=document.getElementById("all");
            var arr= document.getElementsByName("duo");
            if (all.checked){
                for (var i=0;i<arr.length;i++){
                    arr[i].checked="checked";
                }
            }else{
                for (var i=0;i<arr.length;i++){
                    arr[i].checked="";
                }
            }
        }
        function del(){
            var arr=document.getElementsByName("duo");
            var  str="";
            var flag=false;
            for(var i=0;i<arr.length;i++){
                if (arr[i].checked){
                    str+=arr[i].value+",";
                    flag=true;
                }
            }

            if (flag){
                if( confirm('确定删除么')){
                    window.location.href="del?flag=delUserByIds&ids="+str+"";
                }

            }else{
                alert("请至少选择一项")
            }


        }
        function  jump() {
            var jump=document.getElementById("jump1").value;
            if (jump<0){
                jump=1;
            }
            if (jump>=${page.pageCount}){
                jump=${page.pageCount}-1;
            }
            window.location.href="${pageContext.request.contextPath}/searchAllUser?flag=allUser&pageIndex="+jump;
        }
        function sel3() {
            var selObj=document.getElementById("se");
            var pageIndex=selObj.options[selObj.selectedIndex].text;
            window.location.href="${pageContext.request.contextPath}/searchAllUser?flag=allUser&pageIndex="+pageIndex;
        }

    </script>
</head>
<body>
<%@include file="top.jspf"%>



<table>
    <tr>
        <th><input type="checkbox" onclick="t2();" id="all">全选
            <input type="button"  id="del" value="删除" onclick="del();">
        </th><th>编号</th> <th>账号</th> <th>密码</th><th>操作</th></tr>
    <c:if test="${userList.size()>0}">
        <c:forEach items="${userList}" var="u" varStatus="s">
            <tr>
                <td><input type="checkbox" name="duo" id="ii" value="${u.id}" onclick="t1();"></td>
                <td>${s.count}</td>
                <td>${u.userName}</td>
                <td>${u.password}</td>
                <td><a href="${pageContext.request.contextPath}/updateById?flag=updateById&id=${u.id}&pageIndex=${page.pageIndex}">修改</a>
                    ||<a href="${pageContext.request.contextPath}/delById?flag=delById&id=${u.id}&pageIndex=${page.pageIndex}" onclick=" return confirm('确定么？')">删除</a>||
                    <c:if test="${u.status>=3}">
                        <a href="${pageContext.request.contextPath}/suoId?flag=suoId&id=${u.id}&status=${u.status}&pageIndex=${page.pageIndex}">解锁</a>
                    </c:if>
                    <c:if test="${u.status<3}">
                        <a href="${pageContext.request.contextPath}/suoId?flag=suoId&id=${u.id}&status=${u.status}&pageIndex=${page.pageIndex}">上锁</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${userList.size()==0}">
        暂时没有数据
    </c:if>
    <br/>
</table>
&nbsp;&nbsp;&nbsp;
<label>一共有${page.rowCount}条数据   当前第${page.pageIndex}页 ，共${page.pageCount-1}页</label>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <c:choose>
        <c:when test="${page.hasPre}">
            <a href="${pageContext.request.contextPath}/searchAllUser?flag=allUser&pageIndex=1">首页</a>
            <a href="${pageContext.request.contextPath}/searchAllUser?flag=allUser&pageIndex=${page.pageIndex-1}">上一页</a>
        </c:when>
        <c:otherwise>

            首页
            上一页
        </c:otherwise>
    </c:choose>
<c:choose>
    <c:when test="${page.hasNext}">
        <a href="${pageContext.request.contextPath}/searchAllUser?flag=allUser&pageIndex=${page.pageIndex+1}">下一页</a>
        <a href="${pageContext.request.contextPath}/searchAllUser?flag=allUser&pageIndex=${page.pageCount-1}">尾页</a>
    </c:when>
    <c:otherwise>
        下一页
        尾页
    </c:otherwise>
</c:choose>

<input type="text" size="2" id="jump1" value="${page.pageIndex}"><button id="btn" onclick="jump();">跳转</button>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<select id="sel" onchange="jump2();">
    <c:forEach begin="1" end="${pageInfo.pageCount }" var="i">
        <c:if test="${pageInfo.pageIndex==i }">
            <option selected="selected">${i }</option>
        </c:if>
        <c:if test="${pageInfo.pageIndex!=i }">
            <option>${i }</option>
        </c:if>
    </c:forEach>
</select>
<select id="se" onchange="sel3()">
    <c:forEach begin="1" end="${page.pageCount }" var="p" >
        <c:if test="${page.pageIndex==p }">
            <option selected="selected">${p }</option>
        </c:if>
        <c:if test="${page.pageIndex!=p }">
            <option>${p }</option>
        </c:if>
    </c:forEach>
</select>
${msg}
</body>
</html>
