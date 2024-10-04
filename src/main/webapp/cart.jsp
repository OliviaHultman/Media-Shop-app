<%@ page import="ui.UserInfo" %>
<%@ page import="ui.MediaInfo" %>
<%@ page import="java.util.ArrayList" %><%--
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
        <a href="/webshop" class="menu_option">Shop</a>
        <a href="/cart"><img src="img/cart.png"></a>
        <% UserInfo user = (UserInfo) request.getSession().getAttribute("user"); %>
        <% if (user == null) {%>
        <a href="login.jsp">Sign in</a>
        <%} else {%>
        <a href="profile.jsp"><%=user.getFirstName() + " " + user.getLastName()%></a>
        <%}%>
    </div>
</header>
<body>

<div class="content">
    <h1>Cart</h1>
    <% ArrayList<MediaInfo> medias = (ArrayList<MediaInfo>) request.getAttribute("medias"); %>
    <% int totalPrice = 0; %>
    <% for (MediaInfo media : medias) { %>
    <div class="product">
        <%= media.getName() %><br>
        <%= media.getArtist()%><br>
        <%= media.getPrice() + ":-"%><br>
        <% totalPrice += media.getPrice(); %>
    </div>
    <% } %>
    <h2><%= totalPrice + ":-"%></h2>
    <button class="checkout">Checkout</button>
</div>
</body>
</html>
