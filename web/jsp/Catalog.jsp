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

        <div class="row narrow">
            <div class="col-full s-content__header">
                <h1>${title}</h1>

                <p class="lead">${headerP}</p>
            </div>
        </div>

        <div class="row masonry-wrap">
            <div class="masonry">

                <div class="grid-sizer"></div>
                <c:choose>
                    <c:when test="${requestScope.books.size()-1 > 0}">
                        <c:forEach var="book" items="${requestScope.books}">
                            <article class="masonry__brick entry format-standard">

                                <div class="entry__thumb">
                                    <a href="single-standard.html" class="entry__thumb-link">
                                        <img src="../<c:out value="${book.getCoverUrl()}"/>" width="400px" height="400px"
                                             srcset="../<c:out value="${book.getCoverUrl()}"/> 1x, <c:out value="${book.getCoverUrl()}"/> 2x" alt="">
                                    </a>
                                </div>

                                <div class="entry__text">
                                    <div class="entry__header">
                                        <h1 class="entry__title"><a href="single-standard.html"><c:out value="${book.getName()}"/></a></h1>
                                        <div class="entry__date">
                                            <a href="single-standard.html"><c:out value="${book.getPublishYear()}"/></a>
                                        </div>
                                    </div>
                                    <div class="entry__meta">
                                        <form method="post" action="/book">
                                            <input type="hidden" name="command" value="open_book_by_id">
                                            <input type="hidden" name="book_id" value="<c:out value="${book.getId()}"/>">
                                            <button type="submit">More</button>
                                        </form>
                                    </div>
                                </div>

                            </article> <!-- end article -->
                        </c:forEach>
                    </c:when>

                    <c:otherwise>
                        <p>книг нет, сорян</p>
                    </c:otherwise>
                </c:choose>

            </div> <!-- end masonry -->
        </div> <!-- end masonry-wrap -->

        <div class="row">
            <div class="col-full">
                <nav class="pgn">
                    <ul>
                        <li><a class="pgn__prev" href="#0">Prev</a></li>
                        <li><a class="pgn__num" href="#0">1</a></li>
                        <li><span class="pgn__num current">2</span></li>
                        <li><a class="pgn__num" href="#0">3</a></li>
                        <li><a class="pgn__num" href="#0">4</a></li>
                        <li><a class="pgn__num" href="#0">5</a></li>
                        <li><span class="pgn__num dots">…</span></li>
                        <li><a class="pgn__num" href="#0">8</a></li>
                        <li><a class="pgn__next" href="#0">Next</a></li>
                    </ul>
                </nav>
            </div>
        </div>

    </section> <!-- s-content -->

    </body>
</html>
