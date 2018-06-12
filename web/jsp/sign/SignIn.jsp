<%--
  Created by IntelliJ IDEA.
  User: niksk
  Date: 12-Jun-18
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignIn</title>
</head>
    <body>
    <form form id="sign_up" action="/sign_in" method="POST">
        <input type="hidden" name="command" value="sign_in">
        <label for="login">login</label>
        <input type="text" id="login" name="login" required>

        <label for="password">password</label>
        <input type="password" id="password" name="password" required>

        <input type="submit" value="signin">
    </form>
    </body>
</html>
