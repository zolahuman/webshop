<%@ page import="model.dto.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Update User</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
<h1>Update User</h1>

<%
    UserDTO usertoedit = (UserDTO) request.getAttribute("user");
%>

<form method="post" action="updateUser">
    <label for="username">Username:</label>
    <p><%=usertoedit.getUsername()%></p>
    <input type="hidden" id="username" name="usertoedit" value="<%=usertoedit.getUsername()%>" required>
    <br>
    <label for="password">New Password:</label>
    <input type="password" id="password" name="newpassword" value="<%=usertoedit.getPassword()%>" required>
    <br>
    <label for="role">New Role:</label>
    <select id="role" name="newrole" required>
        <option value="user" <%= "user".equals(usertoedit.getRole()) ? "selected" : "" %>>User</option>
        <option value="worker" <%= "worker".equals(usertoedit.getRole()) ? "selected" : "" %>>Worker</option>
        <option value="admin" <%= "admin".equals(usertoedit.getRole()) ? "selected" : "" %>>Admin</option>
    </select>
    <br>
    <button type="submit">Update user</button>

</form>
</body>
</html>
