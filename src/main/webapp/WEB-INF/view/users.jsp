<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: Zola07
  Date: 2024-10-05
  Time: 04:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<html>
<head>
    <title>All Users</title>
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
        <a class="button" href="register.jsp">Register</a>
        <a class="button" href="login.jsp">Login</a>
    </div>
</div>
<%      }
%>
<h1>All Users</h1>

<%
    List<User> userList = (List<User>) request.getAttribute("userList");
    if (userList != null && !userList.isEmpty()) {
%>
<table border="1">
    <tr>
        <th>Username</th>
        <th>Password</th>
        <th>Role</th>
    </tr>
    <%
        for (User user : userList) {
    %>
    <tr>
        <td><%= user.getUsername() %></td>
        <td><%= user.getPassword() %></td>
        <td><%= user.getRole() %></td>
        <td>
        <form method="get" action="getUser">
            <input type="hidden" name="usertoedit" value=<%= user.getUsername() %>>
            <button type="submit">Edit</button>
        </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%
} else {
%>
<p>No users found in the database.</p>
<%
    }
%>

</body>
</html>
