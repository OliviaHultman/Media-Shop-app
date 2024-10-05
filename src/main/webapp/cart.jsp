<%@ page import="ui.UserInfo" %>
<%@ page import="ui.MediaInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ui.OrderInfo" %>
<%@ page import="ui.OrderItemInfo" %>
<%@ page import="bo.Authority" %><%--
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
    <link href="css/webshop.css" rel="stylesheet" type="text/css">
</head>
<header>
    <div class="menu">
        <a href="/webshop" class="menu_left">Shop</a>
        <% UserInfo user = (UserInfo) request.getSession().getAttribute("user"); %>
        <% if (user != null) {%>
        <% if (user.getAuthority() != Authority.CUSTOMER) {%>
        <a href="/orders" class="menu_left">Orders</a>
        <%} else if (user.getAuthority() == Authority.ADMIN) {%>
        <a href="/products" class="menu_left">Products</a>
        <a href="/users" class="menu_left">Users</a>
        <%}%>
        <%}%>
        <a href="/cart" class="menu_right"><img src="img/cart.png"></a>
        <% if (user == null) {%>
        <a href="sign_in.jsp?return=/cart" class="menu_right">Sign in</a>
        <%} else {%>
        <a href="profile.jsp" class="menu_right"><%=user.getFirstName() + " " + user.getLastName()%></a>
        <%}%>
    </div>
</header>
<body>

<div class="content">
    <h1>Cart</h1>
    <% ArrayList<OrderItemInfo> order = (ArrayList<OrderItemInfo>) request.getAttribute("order"); %>
    <% int totalPrice = 0; %>
    <% boolean inStock = true;%>
    <% for (OrderItemInfo item : order) { %>
    <div class="product">
        <p><%= item.getMedia().getName() %><br></p>
        <p><%= item.getMedia().getArtist()%><br></p>
        <p><%= item.getMedia().getPrice() + ":-"%><br></p>
        <p><%= "Number: " + item.getNrOfCopies()%></p>
        <% if (item.getNrOfCopies() > item.getMedia().getNrOfCopies()) {%>
        <p class="warning">Not enough in stock</p>
        <%inStock = false;%>
        <%}%>
        <% totalPrice += (item.getMedia().getPrice() * item.getNrOfCopies()); %>
    </div>
    <% } %>
    <h2><%= totalPrice + ":-"%></h2>
    <% if (!order.isEmpty() && inStock) {%>
        <% String link;%>
        <% if (user == null) {%>
            <% link = "sign_in.jsp?return=/checkout";%>
        <%} else {%>
        <% link = "/checkout";%>
        <%}%>
        <a href=<%=link%>><button class="checkout">Checkout</button></a>
    <%}%>
</div>
</body>
</html>
