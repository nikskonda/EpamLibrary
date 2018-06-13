<%--
  Created by IntelliJ IDEA.
  User: niksk
  Date: 12-Jun-18
  Time: 22:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sign Up</title>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="l10n.local" var="loc" />
    <fmt:message bundle="${loc}" key="local.button.signup.name" var="signup" />
    <fmt:message bundle="${loc}" key="local.lable.login.value" var="login" />
    <fmt:message bundle="${loc}" key="local.lable.password.value" var="password" />
    <fmt:message bundle="${loc}" key="local.lable.confirmPassword.value" var="confirmPasword" />
    <fmt:message bundle="${loc}" key="local.lable.firstName.value" var="firstName" />
    <fmt:message bundle="${loc}" key="local.lable.lastName.value" var="lastName" />
    <fmt:message bundle="${loc}" key="local.lable.email.value" var="email" />
</head>
    <body>
        <form form id="sign_up" action="/sign_up" method="POST">
            <input type="hidden" name="command" value="sign_up">
            <label for="login">${login}</label>
            <input type="text" id="login" name="login" required><br>

            <label for="password">${password}</label>
            <input type="password" id="password" name="password" required><br>

            <label for="confirmPassword">${confirmPassword}</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required><br>

            <label for="firstName">${firstName}</label>
            <input type="text" id="firstName" name="firstName" required><br>

            <label for="lastName">${lastName}</label>
            <input type="text" id="lastName" name="lastName" required><br>

            <label for="email">${email}</label>
            <input type="email" id="email" name="email" required><br>

            <input type="submit" value="${signup}">
        </form>
    </body>
</html>
