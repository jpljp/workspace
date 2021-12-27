<%--
  Created by IntelliJ IDEA.
  User: lin
  Date: 2021/12/22
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <%
    String userName="";
    String password="";
    String status="";
    Cookie[] cookies=request.getCookies();
    if (cookies!=null){
      for (int i=0;i<cookies.length;i++){
        Cookie c=cookies[i];
        if ("userName".equals(c.getName())){
              userName=c.getValue();
        }
        if ("password".equals(c.getName())){
          password=c.getValue();
        }
      }
    }
    if (!userName.equals("")&&!password.equals("")){
      status="checked='checked'";
    }
  %>
    <form action="login?flag=login" method="post">
      用户名： <input name="userName"  value="<%=userName%>"/><br/>
      密码： <input name="password" value="<%=password%>"/><br/>
      <input type="checkbox" name="status" <%=status%>>记住密码一周<br>
      <input type="submit"/><br/>
      <a href="add.jsp">添加新用户</a><br/>
      <label style="color:red">${msg}</label>
    </form>
  </body>
</html>
