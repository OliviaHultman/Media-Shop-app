<%@ page import="ui.UserInfo" %><%--
  Created by IntelliJ IDEA.
  User: Olivia Hultman
  Date: 2024-10-04
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirmation</title>
    <link href="css/webshop.css" rel="stylesheet" type="text/css">
</head>
<header>
    <div class="menu">
        <a href="/webshop" class="menu_left">Shop</a>
        <a href="/cart" class="menu_right"><img src="img/cart.png"></a>
        <% UserInfo user = (UserInfo) request.getSession().getAttribute("user"); %>
        <% if (user == null) {%>
        <a href="login.jsp" class="menu_right">Sign in</a>
        <%} else {%>
        <a href="profile.jsp" class="menu_right"><%=user.getFirstName() + " " + user.getLastName()%></a>
        <%}%>
    </div>
</header>
<body>
    <h1>Order confirmation</h1>
</body>
</html>
