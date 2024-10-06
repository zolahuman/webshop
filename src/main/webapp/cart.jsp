<%--
  Created by IntelliJ IDEA.
  User: Zola07
  Date: 2024-10-04
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>
<html>
<head>
    <title>Cart</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/styles.css">
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
    <a class="button" href="catalog">View Catalog</a>
    <a class="button" href="logout">Log Out</a>
    </div>
</div>
<%
    } else {
%>
    <p>Not logged in???</p>

<%
    }
%>

<div class="container">
    <h1>Cart</h1>

        <%
    // Get the items from request attribute
    List<Item> items = (List<Item>) request.getAttribute("cart");

    if (items != null && !items.isEmpty()) {
        int totalprice = (int) request.getAttribute("totalprice");
        %>
        <div class="cartcontainer">
        <%
        for (Item item : items)
        {%>
            <div class="item">
                <h2><%= item.getName() %></h2>
                <p><%= item.getDescription() %></p>
                <p><%= item.getCategory() %></p>
                <p>Price: <span>$<%= item.getPrice() %></span></p>
                <p>ID: <%= item.getId() %></p>
                <p>Amount: <%= item.getAmount() %>st</p>
            </div><%
        }
        %>
        </div>
        <%

%>


    <form action="order" method="post" class="cartform">
        <p> Total price: <%= totalprice%> </p>
        <!-- Submit each item's id and amount as hidden fields -->
        <%
            for (Item item : items) {
        %>
        <input type="hidden" name="itemId" value="<%= item.getId() %>">
        <input type="hidden" name="amount" value="<%= item.getAmount() %>">
        <%
            }

        %>
        <div class="cartbuttons">
            <a href="emptyCart" class="button">Empty cart</a>
            <button type="submit">Place Order</button>
        </div>
    </form>

<%  }
    else
    {%>
    <p>No items found in the cart.</p>
<%  }%>


</body>
</html>
