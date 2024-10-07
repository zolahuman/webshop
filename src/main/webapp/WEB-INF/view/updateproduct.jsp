<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.dto.ItemDTO" %>
<html>
<head>
    <title>Update Item</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>

<h1>Update Item</h1>

<%
    ItemDTO itemtoupdate = (ItemDTO) request.getAttribute("item");
    if (itemtoupdate != null) {
%>

<form action="updateItem" method="POST">
    <label for="id">Item ID: <%= itemtoupdate.getId() %></label>
    <input type="hidden" id="id" name="id" value="<%= itemtoupdate.getId() %>" readonly>
    <br>

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="<%= itemtoupdate.getName() %>" required>
    <br>

    <label for="category">Category:</label>
    <input type="text" id="category" name="category" value="<%= itemtoupdate.getCategory() %>" required>
    <br>

    <label for="description">Description:</label>
    <input type="text" id="description" name="description" value="<%= itemtoupdate.getDescription() %>" required>
    <br>

    <label for="price">Price:</label>
    <input type="number" id="price" name="price" value="<%= itemtoupdate.getPrice() %>" required>
    <br>

    <label for="amount">Amount:</label>
    <input type="number" id="amount" name="amount" value="<%= itemtoupdate.getAmount() %>" required>
    <br>

    <input type="submit" value="Update Item">
</form>
<%} else {%>
    <p>Item does not exist</p>
<%
    }
%>
</body>
</html>
