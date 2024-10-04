<%--
  Created by IntelliJ IDEA.
  User: Olivia Hultman
  Date: 2024-10-04
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
    <link href="css/webshop.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="sign-up" method="post" class="account">
    <label for="firstName">First name</label>
    <input type="text" id="firstName" name="firstName" placeholder="First name" required>
    <label for="lastName">Last name</label>
    <input type="text" id="lastName" name="lastName" placeholder="Last name" required>
    <label for="email">Email</label>
    <input type="text" id="email" name="email" placeholder="Email" required>
    <label for="password">Password</label>
    <input type="password" id="password" name="password" placeholder="Password" required>
    <div>
        <input type="hidden" name="return" value=<%=request.getParameter("return")%>>
        <button type="submit">Sign up</button>
    </div>
</form>
</body>
</html>
