<%--
  Created by IntelliJ IDEA.
  User: niksk
  Date: 26-Jun-18
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>

    <!-- mobile specific metas
    ================================================== -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <!-- CSS
    ================================================== -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="../../css/base.css">
    <link rel="stylesheet" href="../../css/vendor.css">
    <link rel="stylesheet" href="../../css/main.css">

    <!-- script
    ================================================== -->
    <script src="../../js/modernizr.js"></script>
    <script src="../../js/pace.min.js"></script>

    <!-- favicons
    ================================================== -->
    <link rel="shortcut icon" href="../../favicon.ico" type="image/x-icon">
    <link rel="icon" href="../../favicon.ico" type="image/x-icon">
</head>
<body>

<jsp:include page="../Header.jsp"/>

<section class="s-content">
    <article class="row format-standard">

        <div class="s-content__header col-full">
            <h1 class="s-content__header-title">
                <c:out value="${requestScope.news.getTitle()}"/>
            </h1>
            <ul class="s-content__header-meta">
                <li class="date"><fmt:formatDate type="both" pattern="HH:mm dd-MMM-yy" value="${news.publishDate}"/></li>
                <li class="cat">
                    By <c:out value="${requestScope.news.getUserFirstName()}"/> <c:out value="${requestScope.news.getUserLastName()}"/>
                </li>
            </ul>
        </div> <!-- end s-content__header -->

        <div class="s-content__media col-full">
            <div class="s-content__post-thumb">
                <img src="<c:out value="${requestScope.news.photoUrl}"/>" alt="" >
            </div>
        </div> <!-- end s-content__media -->

        <div class="col-full s-content__main">

            <p><c:out value="${requestScope.news.getText()}"/></p>
        </div>
    </article>

</section>

</body>
</html>
