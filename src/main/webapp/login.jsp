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
    <link href="webshop.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  <form action="login-servlet" method="get" class="login">
    <label for="email">
      Email
    </label>
    <input type="text"
           id="email"
           name="email"
           placeholder="Email" required>

    <label for="password">
      Password
    </label>
    <input type="password"
           id="password"
           name="password"
           placeholder="Password" required>

    <div>
      <button type="submit">
        Login
      </button>
    </div>
  </form>
  </body>
</html>
