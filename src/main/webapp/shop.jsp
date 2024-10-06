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
        <p><%= media.getName() %><br></p>
        <p><%= media.getArtist()%><br></p>
        <p><%= media.getLabel()%><br></p>
        <p><%=media.getType() + ", " + media.getGenre()%><br></p>
        <p><%= media.getPrice() + ":-"%><br></p>
        <% int nrOfCopies = media.getNrOfCopies(); %>
        <% if (nrOfCopies == 0) { %>
        <p style="color: red"><%="Not in stock"%></p>
        <%} else {%>
        <%if (nrOfCopies <= 5) {%>
        <p style="color: gold "><%="Few left in stock"%></p>
        <%} else {%>
        <p style="color: springgreen"> <%="In stock"%></p>
        <%}%>
            <form method="post" action="add-to-cart">
                <input type="hidden" name="ean" value="<%= media.getEan()%>">
                <input type="hidden" name="nrOfCopies" value="<%=nrOfCopies%>">
                <button class="element_button" type="submit">Add to cart +</button>
            </form>
        <% String message = request.getParameter("message");%>
        <% if (message != null && message.equals("out") && request.getParameter("ean").equals(media.getEan())) {%>
        <p class="warning">Not enough in stock</p>
        <%}%>
        <%}%>
    </div>
    <% } %>

</div>
</body>
</html>