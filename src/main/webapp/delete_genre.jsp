<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Olivia Hultman
  Date: 2024-10-06
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete genre</title>
    <link href="css/webshop.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="products" method="post" class="center_element">
    <label for="genre">Genre</label>
    <select name="genre" id="genre" required>
        <% for (String genre : (ArrayList<String>) request.getAttribute("genres")) {%>
        <option value="<%=genre%>"><%=genre%></option>
        <%}%>
    </select>
    <input type="hidden" name="action" value="delete-genre">
    <div>
        <button type="submit">Delete</button>
    </div>
</form>
</body>
</html>
