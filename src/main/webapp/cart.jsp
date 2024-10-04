<%@ page import="ui.UserInfo" %>
<%@ page import="ui.MediaInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ui.OrderInfo" %>
<%@ page import="ui.OrderItemInfo" %><%--
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

<div class="content">
    <h1>Cart</h1>
    <% ArrayList<OrderItemInfo> order = (ArrayList<OrderItemInfo>) request.getAttribute("order"); %>
    <% int totalPrice = 0; %>
    <% for (OrderItemInfo item : order) { %>
    <div class="product">
        <%= item.getMedia().getName() %><br>
        <%= item.getMedia().getArtist()%><br>
        <%= item.getMedia().getPrice() + ":-"%><br>
        <% totalPrice += (item.getMedia().getPrice() * item.getNrOfCopies()); %>
    </div>
    <% } %>
    <h2><%= totalPrice + ":-"%></h2>
    <% String action;%>
    <% if (user == null) {%>
        <% action = "login.jsp";%>
    <%} else {%>
    <% action = "/checkout";%>
    <%}%>
    <form action=<%=action%> method="post">
        <input type="hidden" name="order" value=<%=order%>>
    <button class="checkout" type="submit" >Checkout</button>
    </form>
</div>
</body>
</html>
