<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Index</title>
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="l10n.local" var="loc" />
    <fmt:message bundle="${loc}" key="local.message" var="message" />
    <fmt:message bundle="${loc}" key="local.button.signin.name" var="signin" />
    <fmt:message bundle="${loc}" key="local.button.signup.name" var="signup" />
  </head>
  <body>
  <div>

    <form form action="/controller" method="POST">
      <input type="hidden" name="command" value="ru">
      <input type="submit" value="рус">
    </form>
    <form form action="/controller" method="POST">
      <input type="hidden" name="command" value="en">
      <input type="submit" value="en">
    </form>
  </div>

    ${message}

    <form form id="open_sign_ip" action="/sign_in" method="POST">
      <input type="hidden" name="command" value="open_sign_in">
      <input type="submit" value="${signin}">
    </form>

    <form form id="open_sign_up" action="/sign_up" method="POST">
      <input type="hidden" name="command" value="open_sign_up">
      <input type="submit" value="${signup}">
    </form>

  </body>
</html>