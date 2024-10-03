<%@ page import="ui.UserInfo" %><%--
  Created by IntelliJ IDEA.
  User: Olivia Hultman
  Date: 2024-10-02
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
    <link href="webshop.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="menu">
    <a href="cart.jsp"><img src="img/cart.png"></a>
    <% UserInfo user = (UserInfo) request.getSession().getAttribute("user"); %>
    <% if (user == null) {%>
    <a href="login.jsp">Sign in</a>
    <%} else {%>
    <a href="profile.jsp"><%=user.getFirstName() + " " + user.getLastName()%></a>
    <%}%>
</div>

</body>
</html>
