<%@ page import="bo.Type" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Olivia Hultman
  Date: 2024-10-06
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
    <link href="css/webshop.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="add-product" method="post" class="center_element">
    <label for="ean">EAN</label>
    <input type="text" id="ean" name="ean" placeholder="EAN" required>
    <label for="name">Name</label>
    <input type="text" id="name" name="name" placeholder="Name" required>
    <label for="artist">Artist</label>
    <input type="text" id="artist" name="artist" placeholder="Artist" required>
    <label for="type">Type</label>
    <select name="type" id="type" required>
        <% for (Type type : Type.values()) {%>
        <option value="<%=type%>"><%=type%></option>
        <%}%>
    </select>
    <label for="label">Label</label>
    <input type="text" id="label" name="label" placeholder="Label" required>
    <label for="genre">Genre</label>
    <select name="genre" id="genre" required>
        <% for (String genre : (ArrayList<String>) request.getAttribute("genres")) {%>
        <option value="<%=genre%>"><%=genre%></option>
        <%}%>
    </select>
    <label for="price">Price</label>
    <input type="number" id="price" name="price" placeholder="Price" required>
    <label for="nrOfCopies">Nr in stock</label>
    <input type="number" id="nrOfCopies" name="nrOfCopies" placeholder="Nr in stock" required>
    <div>
        <button type="submit">Add</button>
    </div>
    <% String message = request.getParameter("message");%>
    <% if ("duplicate".equals(message)) {%>
    <p class="warning">EAN is already added</p>
    <%} else if ("format".equals(message)) {%>
    <p class="warning">EAN is incorrect</p>
    <%}%>
</form>
</body>
</html>
