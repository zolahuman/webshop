<%--
  Created by IntelliJ IDEA.
  User: Zola07
  Date: 2024-10-04
  Time: 03:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
<h1>Login</h1>
<form method="POST" action="login" style="text-align: center">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <input type="submit" value="login">
</form>
</body>
</html>
