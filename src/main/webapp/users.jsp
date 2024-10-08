<%@ page import="ui.UserInfo" %>
<%@ page import="bo.Role" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bo.Role" %><%--
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
        <a href="sign_in.jsp?return=users" class="menu_right">Sign in</a>
      <%} else {%>
        <a href="profile.jsp" class="menu_right"><%=user.getFirstName() + " " + user.getLastName()%></a>
      <%}%>
    </div>
  </header>
  <body>
    <div class="content">
      <h1>Users</h1>
      <div class="side_element">
        <a href="/create_user.jsp?return=users"><button class="left_side_button">Add user</button></a>
      </div>
      <% ArrayList<UserInfo> users = (ArrayList<UserInfo>) request.getAttribute("users"); %>
      <% for (UserInfo userInfo : users) { %>
        <div class="element">
          <form action="users?action=update" method="post">
            <b><label for="email">Email:</label></b>
            <input type="text" id="email" name="email" value="<%=userInfo.getEmail()%>" required readonly><br>
            <b><label for="firstName">First name:</label></b>
            <input type="text" name="firstName" id="firstName" value="<%=userInfo.getFirstName()%>" required><br>
            <b><label for="lastName">Last name:</label></b>
            <input name="lastName" id="lastName" value="<%=userInfo.getLastName()%>" required><br>
            <input type="hidden" name="password" value="<%=userInfo.getPassword()%>">
            <b><label for="role">Role:</label></b>
            <select name="role" id="role" required>
              <% for (Role role : Role.values()) {%>
                <% String selected = "";%>
                <% if (role == userInfo.getRole()) {%>
                  <%selected = "selected";%>
                <%}%>
                <option value="<%=role%>" <%=selected%>><%=role%></option>
              <%}%>
            </select><br>
            <input type="hidden" name="return" value="users">
            <button type="submit" class="element_button">Update</button>
            <button type="submit" class="element_button" formaction="users?action=delete">Delete</button>
          </form>
        </div>
      <% } %>
    </div>
  </body>
</html>
