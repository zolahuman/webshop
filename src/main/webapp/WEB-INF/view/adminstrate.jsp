<%--
  Created by IntelliJ IDEA.
  User: Zola07
  Date: 2024-10-05
  Time: 02:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administrate</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
<h1>Update User</h1>
<form method="post" action="updateUser">
    <label for="username">Username:</label>
    <p id="username"></p>
    <input type="hidden" id="username" name="username" required>
    <br>
    <label for="password">New Password:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <label for="role">New Role:</label>
    <select id="role" name="role" required>
        <option value="user">User</option>
        <option value="worker">Worker</option>
        <option value="admin">Admin</option>
    </select>
    <br>
    <input type="submit" value="Update User">
</form>
</body>
</html>
