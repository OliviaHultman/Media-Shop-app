<%@ page import="ui.UserInfo" %>
<%@ page import="bo.UserHandler" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!!" %></h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
<%UserInfo userInfo = UserHandler.getUser("oliviahu@kth.se"); %>>
<%=userInfo.getFirstName()%>
<%=userInfo.getLastName()%>
</html>