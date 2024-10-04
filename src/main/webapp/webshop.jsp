<%@ page import="ui.MediaInfo" %>
<%@ page import="bo.MediaHandler" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ui.UserInfo" %>
<%@ page import="bo.User" %>
<%@ page import="bo.Authority" %><%--
  Created by IntelliJ IDEA.
  User: Olivia Hultman
  Date: 2024-10-02
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Shop</title>
  <link href="css/webshop.css" rel="stylesheet" type="text/css">
</head>
<header>
    <div class="menu">
        <a href="/webshop" class="menu_left">Shop</a>
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
        <a href="sign_in.jsp?return=/webshop" class="menu_right">Sign in</a>
        <%} else {%>
        <a href="profile.jsp" class="menu_right"><%=user.getFirstName() + " " + user.getLastName()%></a>
        <%}%>
    </div>
</header>
<body>
<div class="content">
    <h1>Shop</h1>
    <% ArrayList<MediaInfo> medias = (ArrayList<MediaInfo>) request.getAttribute("medias"); %>
    <% for (MediaInfo media : medias) { %>
    <div class="product">
        <%= media.getName() %><br>
        <%= media.getArtist()%><br>
        <%= media.getPrice() + ":-"%><br>
        <% int nrOfCopies = media.getNrOfCopies(); %>
        <% if (nrOfCopies == 0) { %>
            <%="Not in stock"%>
        <%} else {%>
        <%if (nrOfCopies <= 5) {%>
        <%="Few left in stock"%>
        <%} else {%>
        <%="In stock"%>
        <%}%>
            <form method="post" action="add-to-cart">
                <input type="hidden" name="ean" value="<%= media.getEan()%>">
                <button class="add" type="submit">Add to cart +</button>
            </form>

        <%} %>
    </div>
    <% } %>

</div>
</body>
</html>
