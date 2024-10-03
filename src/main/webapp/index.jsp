<%@ page import="ui.MediaInfo" %>
<%@ page import="bo.MediaHandler" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Olivia Hultman
  Date: 2024-10-02
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Home</title>
  <link href="webshop.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="menu">
    <a href="login.jsp" class="menu_option">Sign in</a>
    <a href="cart.jsp" class="cart_icon"><img src="img/cart.png"></a>
</div>
<div class="content">
    <h1>Music shop</h1>
    <!--
    <% ArrayList<MediaInfo> mediasInfo = MediaHandler.getMedias(); %>
    <% for (MediaInfo mediaInfo : mediasInfo) { %>
    <div class="product">
        <%= mediaInfo.getName() %><br>
        <%= mediaInfo.getArtist()%><br>
        <%= mediaInfo.getPrice() + ":-"%><br>
        <button class="add">Add to cart +</button>
    </div>
    <% } %>
    -->
</div>
</body>
</html>
