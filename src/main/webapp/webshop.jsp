<%@ page import="ui.MediaInfo" %>
<%@ page import="bo.MediaHandler" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ui.UserInfo" %><%--
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
    <% UserInfo user = (UserInfo) request.getSession().getAttribute("user"); %>
    <% if (user != null) {%>
    <%=user.getEmail()%>
    <%}%>
    <% ArrayList<MediaInfo> medias = (ArrayList<MediaInfo>) request.getSession().getAttribute("medias"); %>
    <% for (MediaInfo media : medias) { %>
    <div class="product">
        <%= media.getName() %><br>
        <%= media.getArtist()%><br>
        <%= media.getPrice() + ":-"%><br>
        <form method="post" action="webshop">
            <input type="hidden" name="ean" value="<%= media.getEan()%>">
            <input type="hidden" name="command" value="addToCart">
        <button class="add" type="submit">Add to cart +</button>
        </form>
    </div>
    <% } %>

</div>
</body>
</html>
