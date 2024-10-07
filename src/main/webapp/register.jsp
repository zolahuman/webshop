<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
<h1>Register</h1>
<form method="POST" action="register" style="text-align: center">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <label for="role">Role:</label>
    <select id="role" name="role" required>
        <option value="user">User</option>
        <option value="worker">Worker</option>
        <option value="admin">Admin</option>
    </select>
    <br>
    <input type="submit" value="Register">
</form>
</body>
</html>
