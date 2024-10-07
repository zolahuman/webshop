<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
    <title>New product</title>
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
        <a class="button" href="getUsers">Users</a>
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
        <a class="button" href="../../register.jsp">Register</a>
        <a class="button" href="../../login.jsp">Login</a>
    </div>
</div>
<%      }
%>

<form action="additem" method="POST">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name"  required>
    <br>

    <label for="category">Category:</label>
    <input type="text" id="category" name="category" required>
    <br>

    <label for="description">Description:</label>
    <input type="text" id="description" name="description" required>
    <br>

    <label for="price">Price:</label>
    <input type="number" id="price" name="price" required min="0">
    <br>

    <label for="amount">Amount:</label>
    <input type="number" id="amount" name="amount" required min="0">
    <br>

    <input type="submit" value="Add Item">
</form>
</body>
</html>
