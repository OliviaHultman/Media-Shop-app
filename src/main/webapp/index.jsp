<%@ page import="ui.UserInfo" %>
<%@ page import="bo.UserHandler" %>
<%@ page import="bo.Media" %>
<%@ page import="ui.MediaInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bo.MediaHandler" %>
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
<%UserInfo userInfo = UserHandler.getUser("oliviahu@kth.se"); %>
<%=userInfo.getFirstName()%>
<%=userInfo.getLastName()%>
<% ArrayList<MediaInfo> mediasInfo = MediaHandler.getUserMedias("oliviahu@kth.se");%>
<% for (MediaInfo mediaInfo : mediasInfo) {%>
  <%=mediaInfo.getName()%>
<%}%>
</html>