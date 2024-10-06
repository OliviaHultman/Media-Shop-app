<%--
  Created by IntelliJ IDEA.
  User: Olivia Hultman
  Date: 2024-10-06
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add genre</title>
  <link href="css/webshop.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="add-genre" method="post" class="center_element">
  <label for="genre">Genre</label>
  <input type="text" id="genre" name="genre" placeholder="Genre" required>
  <div>
    <button type="submit">Add</button>
  </div>
</form>
</body>
</html>
