<%@ page import="java.util.ArrayList" %>
<%@ page import="bo.Authority" %>
<%@ page import="ui.*" %><%--
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
        <%}%>
        <%if (user.getAuthority() == Authority.ADMIN) {%>
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
    <% ArrayList<MediaItemInfo> order = (ArrayList<MediaItemInfo>) request.getAttribute("order"); %>
    <% int totalPrice = 0; %>
    <% boolean inStock = true;%>
    <% for (MediaItemInfo item : order) { %>
    <div class="element">
        <p><b>EAN:&emsp;</b><%=item.getMedia().getEan()%></p>
        <p><b>Name:&emsp;</b><%= item.getMedia().getName() %><br></p>
        <p><b>Artist:&emsp;</b><%= item.getMedia().getArtist()%><br></p>
        <p><b>Label:&emsp;</b><%= item.getMedia().getLabel()%><br></p>
        <p><b>Categories:&emsp;</b><%=item.getMedia().getType() + ", " + item.getMedia().getGenre()%><br></p>
        <p><b>Price:&emsp;</b><%= item.getMedia().getPrice() + ":-"%><br></p>
        <form action="update-cart" method="post">
            <b><label for="nrOfCopies">Number</label></b>
            <input type="text" id="nrOfCopies" name="nrOfCopiesCart" value="<%=item.getNrOfCopies()%>" required>
            <input type="hidden" name="nrOfCopiesStock" value="<%=item.getMedia().getNrOfCopies()%>">
            <input type="hidden" name="ean" value="<%=item.getMedia().getEan()%>">
            <button type="submit" class="element_button">Update</button>
        </form>
        <% if (item.getNrOfCopies() > item.getMedia().getNrOfCopies()) {%>
        <p class="warning">Not enough in stock</p>
        <%inStock = false;%>
        <%}%>
        <% totalPrice += (item.getMedia().getPrice() * item.getNrOfCopies()); %>
    </div>
    <% } %>
    <div class="side_element">
    <h2><%= totalPrice + ":-"%></h2>
    <% String disabled = "";%>
    <% if (order.isEmpty() || !inStock) {%>
    <%disabled = "disabled";%>
    <%}%>
        <% String link;%>
        <% if (user == null) {%>
            <% link = "sign_in.jsp?return=/checkout";%>
        <%} else {%>
        <% link = "/checkout";%>
        <%}%>
        <a href=<%=link%>><button class="right_side_button" <%=disabled%>>Checkout</button></a>
    </div>
</div>
</body>
</html>
