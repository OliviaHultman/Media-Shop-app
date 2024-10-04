<%--
  Created by IntelliJ IDEA.
  User: Olivia Hultman
  Date: 2024-10-02
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Sign in</title>
    <link href="css/webshop.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  <form action="sign-in" method="post" class="account">
    <label for="email">Email</label>
    <input type="text" id="email" name="email" placeholder="Email" required>
    <label for="password">Password</label>
    <input type="password" id="password" name="password" placeholder="Password" required>
    <a href="sign_up.jsp?return=<%=request.getParameter("return")%>">Sign up</a>
    <div>
      <input type="hidden" name="return" value=<%=request.getParameter("return")%>>
      <button type="submit">Sign in</button>
    </div>
    <% String message = request.getParameter("message");%>
    <% if (message != null && message.equals("wrong")) {%>
      <p class="warning">Wrong email or password</p>
    <%}%>
  </form>
  </body>
</html>
