<%@ page import="ui.UserInfo" %>
<%@ page import="bo.Authority" %><%--
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
            <a href="/shop" class="menu_left">Shop</a>
            <% UserInfo user = (UserInfo) request.getSession().getAttribute("user"); %>
            <% if (user != null) {%>
            <% if (user.getAuthority() != Authority.CUSTOMER) {%>
            <a href="/orders" class="menu_left">Orders</a>
            <%}%>
            <%if (user.getAuthority() == Authority.ADMIN) {%>
            <a href="/products" class="menu_left">Products</a>
            <a href="/users" class="menu_left">Users</a>
            <%}%>
            <%}%>
            <a href="/cart" class="menu_right"><img src="img/cart.png"></a>
            <% if (user == null) {%>
            <a href="sign_in.jsp?return=/shop" class="menu_right">Sign in</a>
            <%} else {%>
            <a href="profile.jsp" class="menu_right"><%=user.getFirstName() + " " + user.getLastName()%></a>
            <%}%>
        </div>
    </header>
    <body>
        <h1>Order confirmation</h1>
        <p class="confirmation">Your order has been sent to us! We will handle it as soon as possible. </p>
    </body>
</html>
