<%@ page import="ui.MediaInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ui.UserInfo" %>
<%@ page import="bo.Role" %>
<%@ page import="bo.Role" %><%--
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
            <a href="/shop" class="menu_left">Shop</a>
            <% UserInfo user = (UserInfo) request.getSession().getAttribute("user"); %>
            <% if (user != null) {%>
                <% if (user.getRole() != Role.CUSTOMER) {%>
                    <a href="/orders" class="menu_left">Orders</a>
                <%}%>
                <%if (user.getRole() == Role.ADMIN) {%>
                    <a href="/products" class="menu_left">Products</a>
                    <a href="/users" class="menu_left">Users</a>
                <%}%>
            <%}%>
            <a href="/cart" class="menu_right"><img src="img/cart.png"></a>
            <% if (user == null) {%>
                <a href="sign_in.jsp?return=shop" class="menu_right">Sign in</a>
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
                <div class="element">
                    <p><b>EAN:&emsp;</b><%=media.getEan()%></p>
                    <p><b>Name:&emsp;</b><%= media.getName() %><br></p>
                    <p><b>Artist:&emsp;</b><%= media.getArtist()%><br></p>
                    <p><b>Label:&emsp;</b><%= media.getLabel()%><br></p>
                    <p><b>Categories:&emsp;</b><%=media.getType() + ", " + media.getGenre()%><br></p>
                    <p><b>Price:&emsp;</b><%= media.getPrice() + ":-"%><br></p>
                    <% int nrOfCopies = media.getNrOfCopies(); %>
                    <% String disabled = "";%>
                    <% if (nrOfCopies <= 0) { %>
                        <p style="color: red"><%="Not in stock"%></p>
                        <% disabled = "disabled";%>
                    <%} else if (nrOfCopies <= 5){%>
                        <p style="color: gold "><%="Few left in stock"%></p>
                    <%} else {%>
                        <p style="color: springgreen"> <%="In stock"%></p>
                    <%}%>
                    <form method="post" action="cart">
                        <input type="hidden" name="ean" value="<%= media.getEan()%>">
                        <input type="hidden" name="nrOfCopies" value="<%=nrOfCopies%>">
                        <input type="hidden" name="action" value="add">
                        <button class="element_button" type="submit" <%=disabled%>>Add to cart</button>
                    </form>
                    <% String message = request.getParameter("message");%>
                    <% if (message != null && message.equals("out") && request.getParameter("ean").equals(media.getEan())) {%>
                        <p class="warning">Not enough in stock</p>
                    <%} else {%>
                        <br>
                    <%}%>
                </div>
            <% } %>
        </div>
    </body>
</html>