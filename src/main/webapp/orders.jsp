<%@ page import="ui.UserInfo" %>
<%@ page import="bo.Authority" %>
<%@ page import="ui.OrderInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ui.OrderItemInfo" %>
<%@ page import="bo.Status" %><%--
  Created by IntelliJ IDEA.
  User: Olivia Hultman
  Date: 2024-10-04
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
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
    <h1>Orders</h1>
    <% ArrayList<OrderInfo> orders = (ArrayList<OrderInfo>) request.getAttribute("orders"); %>
    <% for (OrderInfo order : orders) { %>
    <div class="element_form">
        <p><%= order.getOrderNr() %><br></p>
        <p><%= order.getEmail()%><br></p>
        <% ArrayList<OrderItemInfo> items = order.getItems(); %>
        <% for (int i = 0; i < items.size(); i++) {%>
            <%="Product" + i + ": " + items.get(i).getEan() + "\tNumber: " + items.get(i).getNrOfCopies()%>
        <%}%>
        <form action="change-status" method="post">
            <label for="status">Status</label>
            <select name="status" id="status" required>
                <% for (Status status : Status.values()) {%>
                <% String selected = "";%>
                <% if (status == order.getStatus()) {%>
                <%selected = "selected";%>
                <%}%>
                <option value="<%=status%>" <%=selected%>>
                    <%=status%>
                </option>
                <%}%>
            </select>
            <input type="hidden" name="orderNr" value="<%=order.getOrderNr()%>">
            <button type="submit" class="element_button">Update</button>
        </form>
    </div>
    <% } %>

</div>
</body>
</html>
