<%@ page import="java.util.List" %>
<%@ page import="model.dto.orderDTO" %>
<%@ page import="model.dto.ItemDTO" %><%--
  Created by IntelliJ IDEA.
  User: Zola07
  Date: 2024-10-05
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<%
    // Get the items from request attribute
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
        <% if (role.equals("admin")){%>
        <a class="button" href="getOrders">Handle Orders</a>
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
    <h1>Orders</h1>

    <%
        List<orderDTO> orders = (List<orderDTO>) request.getAttribute("orders");
        if (orders != null) {
    %>
    <div class="ordercontainer">
        <% for (orderDTO order : orders) { %>
        <div class="order">
            <div>
                <h2>Order id: <%= order.getOrderId() %></h2>
                <h4>Order by: <%= order.getOrderBy() %></h4>
                <h4><%= order.getStatus() %></h4>
            </div>

            <%
            List<ItemDTO> items = order.getItems();
            for (ItemDTO item  : items) { %>
            <div class="item">
                <h2><%= item.getName() %></h2>
                <p><%= item.getCategory() %></p>
                <p>Price: <span>$<%= item.getPrice() %></span></p>
                <p>ID: <%= item.getId() %></p>
                <p>Amount: <%= item.getAmount() %>st</p>
            </div>
            <%}
            if (username != null) { %>
            <div class="productbuttoncontainer">
                <% if (request.getAttribute("role").equals("worker")) { %>

                <form action="updateOrder" method="post" class="leftbutton">
                    <input type="hidden" name="orderId" value="<%= order.getOrderId() %>">
                    <input type="hidden" name="orderStatus" value="<%= order.getStatus() %>">
                    <button type="submit">Change status</button>
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
