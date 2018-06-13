<%--
  Created by IntelliJ IDEA.
  User: niksk
  Date: 12-Jun-18
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SignIn</title>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="l10n.local" var="loc" />
    <fmt:message bundle="${loc}" key="local.button.signin.name" var="signin" />
    <fmt:message bundle="${loc}" key="local.lable.login.value" var="login" />
    <fmt:message bundle="${loc}" key="local.lable.password.value" var="password" />

</head>
    <body>
    <form form id="sign_up" action="/sign_in" method="POST">
        <input type="hidden" name="command" value="sign_in">
        <label for="login">${login}</label>
        <input type="text" id="login" name="login" required><br>

        <label for="password">${password}</label>
        <input type="password" id="password" name="password" required><br>

        <input type="submit" value="${signin}">
    </form>
    </body>
</html>
