<%@ page import="ui.UserInfo" %>
<%@ page import="bo.Authority" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Olivia Hultman
  Date: 2024-10-04
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Users</title>
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
  <h1>Users</h1>
  <a href="/create_user.jsp?return=/users"><button class="side_button">Add user +</button></a>
  <% ArrayList<UserInfo> users = (ArrayList<UserInfo>) request.getAttribute("users"); %>
  <% for (UserInfo userInfo : users) { %>
  <div class="element_form">
    <form action="update-user" method="post">
      <label for="email">Email</label>
      <input type="text" id="email" name="email" value="<%=userInfo.getEmail()%>" required readonly>
      <label for="firstName">First name</label>
      <input type="text" name="firstName" id="firstName" value="<%=userInfo.getFirstName()%>" required>
      <label for="lastName">Last name</label>
      <input name="lastName" id="lastName" value="<%=userInfo.getLastName()%>" required>
      <input type="hidden" name="password" value="<%=userInfo.getPassword()%>">
      <label for="authority">Role</label>
      <select name="authority" id="authority" required>
        <% for (Authority authority : Authority.values()) {%>
        <% String selected = "";%>
        <% if (authority == userInfo.getAuthority()) {%>
        <%selected = "selected";%>
        <%}%>
        <option value="<%=authority%>" <%=selected%>>
        <%=authority%>
        </option>
        <%}%>
      </select>
      <input type="hidden" name="return" value="/users">
      <button type="submit" class="element_button">Update</button>
    </form>
  </div>
  <% } %>

</div>
</body>
</html>
