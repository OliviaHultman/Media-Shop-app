<%@ page import="ui.UserInfo" %>
<%@ page import="bo.Role" %>
<%@ page import="bo.Role" %><%--
  Created by IntelliJ IDEA.
  User: Olivia Hultman
  Date: 2024-10-04
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Create user</title>
        <link href="css/webshop.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <form action="users" method="post" class="center_element">
            <label for="firstName">First name</label>
            <input type="text" id="firstName" name="firstName" placeholder="First name" required>
            <label for="lastName">Last name</label>
            <input type="text" id="lastName" name="lastName" placeholder="Last name" required>
            <label for="email">Email</label>
            <input type="email" id="email" name="email" placeholder="Email" required>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Password" required>
            <% UserInfo user = (UserInfo) request.getSession().getAttribute("user");%>
            <%if (user != null && user.getRole() == Role.ADMIN) {%>
                <label for="role">Role</label>
                <select name="role" id="role" required>
                    <% for (Role role : Role.values()) {%>
                        <option value="<%=role%>"><%=role%></option>
                    <%}%>
                </select>
            <%}%>
            <input type="hidden" name="return" value=<%=request.getParameter("return")%>>
            <input type="hidden" name="action" value="create">
            <div>
                <button type="submit">Create</button>
            </div>
            <% String message = request.getParameter("message");%>
            <% if (message != null && message.equals("duplicate")) {%>
               <p class="warning">Email is already used</p>
            <%}%>
        </form>
    </body>
</html>
