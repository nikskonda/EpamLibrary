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

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="l10n.local" var="loc" />
<fmt:message bundle="${loc}" key="local.message.catalog.title" var="title" />
<fmt:message bundle="${loc}" key="local.message.catalog.header" var="headerP" />


<html>
<head>
    <title>Catalog</title>

    <!-- CSS
    ================================================== -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/base.css">
    <link rel="stylesheet" href="../css/vendor.css">
    <link rel="stylesheet" href="../css/main.css">

    <!-- script
    ================================================== -->
    <script src="../js/modernizr.js"></script>
    <script src="../js/pace.min.js"></script>

    <!-- favicons
    ================================================== -->
    <link rel="shortcut icon" href="../favicon.ico" type="image/x-icon">
    <link rel="icon" href="../favicon.ico" type="image/x-icon">

</head>
    <body style="background-color: black">

    <jsp:include page="Header.jsp"/>

    <section class="s-content">
        <div class="container">
            <c:out value="${requestScope.text}"/>
        </div>

    </section> <!-- s-content -->

    </body>
</html>
