<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.dto.ItemDTO" %>
<html>
<head>
    <title>Web shop catalog</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>

<%
    String username = (String) request.getAttribute("username");
    if (username != null)
        {
            String role = (String) request.getAttribute("role");
%>
    <div class="topofsite">
        <div class="userinfocontainer">
            <p>Logged in as: <%= username %> </p>
            <p>User role: <%= role %> </p>
        </div>
        <div class="buttoncontainer">
            <% if (role.equals("admin")){%>
                <a class="button" href="showAddItemForm">Add Product</a>
                <a class="button" href="getUsers">Users</a>
            <%}%>
            <% if (role.equals("worker")){%>
            <a class="button" href="getAllOrders">Handle Orders</a>
            <%}%>
            <a class="button" href="catalog">Catalog</a>
            <a class="button" href="getcart">Check cart</a>
            <a class="button" href="logout">Log Out</a>
        </div>
    </div>

<%      }
    else {
%>
<div class="topofsite">
<p>not logged in</p>
    <div class="buttoncontainer">
        <a class="button" href="register.jsp">Register</a>
        <a class="button" href="login.jsp">Login</a>
    </div>
</div>
<%      }
%>
<div class="container">
    <h1>Catalog</h1>

    <%
        List<ItemDTO> items = (List<ItemDTO>) request.getAttribute("items");
        if (items != null) {
    %>
    <div class="itemcontainer">
        <% for (ItemDTO item : items) { %>
        <div class="item">
            <h2><%= item.getName() %></h2>
            <p><%= item.getDescription() %></p>
            <p><%= item.getCategory() %></p>
            <p>Price: <span>$<%= item.getPrice() %></span></p>
            <p>ID: <%= item.getId() %></p>
            <p>Warehouse status: <%= item.getAmount() %>st available</p>
            <% if (username != null) { %>
            <div class="productbuttoncontainer">
            <% if (request.getAttribute("role").equals("admin")) { %>

                <form action="getItem" method="get" class="leftbutton">
                    <input type="hidden" name="itemId" value="<%= item.getId() %>">
                    <button type="submit">Edit product</button>
                </form>

            <% } %>
            <% if (item.getAmount() > 0) { %>
            <form action="addToCart" method="post" class="rightbutton">
                <input type="hidden" name="itemId" value="<%= item.getId() %>">
                <button type="submit">Add to cart</button>
            </form>
            <% } %>
            </div>
            <% } %>
        </div>
        <% } %>
    </div>
    <% } else { %>
    <p>No items found in the catalog.</p>
    <% } %>
</div>
</body>
</html>
