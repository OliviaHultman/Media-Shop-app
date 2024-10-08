<%@ page import="ui.UserInfo" %>
<%@ page import="bo.Role" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ui.MediaInfo" %>
<%@ page import="bo.Type" %>
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
        <title>Products</title>
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
                <a href="sign_in.jsp?return=products" class="menu_right">Sign in</a>
            <%} else {%>
                <a href="profile.jsp" class="menu_right"><%=user.getFirstName() + " " + user.getLastName()%></a>
            <%}%>
        </div>
    </header>
    <body>
        <div class="content">
            <h1>Products</h1>
            <div class="side_element">
                <a href="/products?action=init&return=add_product.jsp"><button class="left_side_button">Add product</button></a>
                <a href="/products?action=init&return=delete_genre.jsp"><button class="right_side_button">Delete genre</button></a>
                <a href="/add_genre.jsp"><button class="right_side_button">Add genre</button></a>
            </div>
            <% ArrayList<MediaInfo> products = (ArrayList<MediaInfo>) request.getAttribute("products"); %>
            <% for (MediaInfo product : products) { %>
                <div class="element">
                    <form action="products?action=update" method="post">
                        <b><label for="ean">EAN:</label></b>
                        <input type="text" id="ean" name="ean" value="<%=product.getEan()%>" required readonly><br>
                        <b><label for="name">Name:</label></b>
                        <input type="text" name="name" id="name" value="<%=product.getName()%>" required><br>
                        <b><label for="artist">Artist:</label></b>
                        <input name="artist" id="artist" value="<%=product.getArtist()%>" required><br>
                        <b><label for="type">Type:</label></b>
                        <select name="type" id="type" required>
                            <% for (Type type : Type.values()) {%>
                                <% String selected = "";%>
                                <% if (type == product.getType()) {%>
                                    <%selected = "selected";%>
                                <%}%>
                                <option value="<%=type%>" <%=selected%>><%=type%></option>
                            <%}%>
                        </select><br>
                        <b><label for="label">Label:</label></b>
                        <input name="label" id="label" value="<%=product.getLabel()%>" required><br>
                        <b><label for="genre">Genre:</label></b>
                        <select name="genre" id="genre" required>
                            <% for (String genre : (ArrayList<String>) request.getAttribute("genres")) {%>
                                <% String selected = "";%>
                                <% if (genre.equals(product.getGenre())) {%>
                                    <%selected = "selected";%>
                                <%}%>
                                <option value="<%=genre%>" <%=selected%>><%=genre%></option>
                            <%}%>
                        </select><br>
                        <b><label for="price">Price:</label></b>
                        <input name="price" id="price" value="<%=product.getPrice()%>" required><br>
                        <b><label for="nrOfCopies">In stock:</label></b>
                        <input name="nrOfCopies" id="nrOfCopies" value="<%=product.getNrOfCopies()%>" required>
                        <button type="submit" class="element_button">Update</button>
                        <button type="submit" class="element_button" formaction="products?action=delete">Delete</button>
                    </form>
                </div>
            <% } %>
        </div>
    </body>
</html>
