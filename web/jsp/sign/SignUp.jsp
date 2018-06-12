<%--
  Created by IntelliJ IDEA.
  User: niksk
  Date: 12-Jun-18
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
    <body>
        <form form id="sign_up" action="/sign_up" method="POST">
            <input type="hidden" name="command" value="sign_up">
            <label for="login">login</label>
            <input type="text" id="login" name="login" required>

            <label for="password">password</label>
            <input type="password" id="password" name="password" required>

            <label for="confirmPasword">confirmPasword</label>
            <input type="password" id="confirmPasword" name="confirmPasword" required>

            <label for="firstName">firstName</label>
            <input type="text" id="firstName" name="firstName" required>

            <label for="secondName">login</label>
            <input type="text" id="secondName" name="secondName" required>

            <label for="email">email</label>
            <input type="email" id="email" name="email" required>

            <input type="submit" value="signup">
        </form>
    </body>
</html>
