<%--
  Created by IntelliJ IDEA.
  User: Zola07
  Date: 2024-10-04
  Time: 00:34
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="model.User"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Success JSP Page</title>
</head>
<body>
<p>You are successfully logged in!</p>
<%
    User bean = (User) request.getAttribute("bean");
    out.print("Welcome, " + bean.getUsername());
%>
<%@ include file="../../catalog.jsp" %>
</body>
</html>