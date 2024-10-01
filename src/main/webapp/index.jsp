<%@ page import="ui.UserInfo" %>
<%@ page import="ui.MediaInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bo.*" %>
<%@ page import="java.sql.Date" %>
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
<%UserHandler.addToCart(new MediaInfo("0194397051223", "Fine Line", "Harry Styles", Category.LP, "Columbia", Genre.POP,
        new Date(119, 11, 13), "", 494, 50), userInfo);%>
<!-- UserHandler.removeFromCart(new MediaInfo("0194397051223", "Fine Line", "Harry Styles", Category.LP, "Columbia", Genre.POP,
        new Date(119, 11, 13), "", 494, 50), userInfo);-->
</html>