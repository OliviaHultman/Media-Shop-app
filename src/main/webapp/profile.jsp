<%@ page import="ui.UserInfo" %>
<%@ page import="bo.Authority" %><%--
  Created by IntelliJ IDEA.
  User: Olivia Hultman
  Date: 2024-10-04
  Time: 01:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% if (request.getSession().getAttribute("user") == null) {%>
<meta http-equiv="Refresh" content="0; URL=/sign_in.jsp?return=/profile.jsp">
<%} else {%>
<head>
    <title>Profile</title>
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
        <a href="sign_in.jsp?return=/profile" class="menu_right">Sign in</a>
        <%} else {%>
        <a href="profile.jsp" class="menu_right"><%=user.getFirstName() + " " + user.getLastName()%></a>
        <%}%>
    </div>
</header>
<body>
<div class="content">
    <h1>My profile</h1>
    <a href="/sign-out"><button class="add_user">Sign out</button></a>
    <div class="profile">
        <form action="update-user" method="post">
            <label for="email">Email</label>
            <input type="text" id="email" name="email" value="<%=user.getEmail()%>" required readonly>
            <label for="firstName">First name</label>
            <input type="text" name="firstName" id="firstName" value="<%=user.getFirstName()%>" required>
            <label for="lastName">Last name</label>
            <input name="lastName" id="lastName" value="<%=user.getLastName()%>" required>
            <label for="password">Password</label>
            <input type="password" name="password" id="password" value="<%=user.getPassword()%>" required>
            <% if (user.getAuthority() != Authority.ADMIN) { %>
            <input type="hidden" name="authority" value="<%=String.valueOf(user.getAuthority())%>">
            <%} else {%>
            <label for="authority">Authority</label>
            <select name="authority" id="authority" required>
                <% for (Authority authority : Authority.values()) {%>
                <% String selected = "";%>
                <% if (authority == user.getAuthority()) {%>
                <%selected = "selected";%>
                <%}%>
                <option value="<%=authority%>" <%=selected%>>
                    <%=authority%>
                </option>
                <%}%>
            </select>
            <%}%>
            <input type="hidden" name="return" value="/profile.jsp">
            <button type="submit" class="add">Update</button>
        </form>
    </div>

</div>
</body>
<%}%>
</html>
