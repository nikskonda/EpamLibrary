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

        <div class="row narrow">
            <form method="post" action="/book">
                <input type="hidden" name="command" value="book_search">
                <input type="text" name="search" value="<c:out value="${requestScope.search}"/>">
                <button type="submit">Find me plz</button>
            </form>
        </div>

        <div class="row masonry-wrap">
            <div class="masonry">

                <div class="grid-sizer"></div>
                <c:choose>
                    <c:when test="${requestScope.books.size()>0}">
                        <c:forEach var="book" items="${requestScope.books}">

                            <div class="col-lg-3" style="border: grey solid 2px; height: 600px; margin: 5px;">
                                <img src="../<c:out value="${book.getCoverUrl()}"/>" width="200px" height="200px" alt="">
                                <div class="entry__text">
                                    <div class="entry__header">
                                        <h1 class="entry__title"><c:out value="${book.getName()}"/></h1>
                                    </div>
                                    <div class="entry__date">
                                        <p><c:out value="${book.getPublishYear()}"/></p>
                                    </div>
                                </div>
                                <div class="entry__meta">
                                    <form method="post" action="/book">
                                        <input type="hidden" name="command" value="open_book_by_id">
                                        <input type="hidden" name="book_id" value="<c:out value="${book.id}"/>">
                                        <button type="submit">More</button>
                                    </form>
                                </div>
                            </div>
                            <%--<article class="masonry__brick entry format-standard">--%>

                                <%--<div class="entry__thumb">--%>
                                    <%--<a href="single-standard.html" class="entry__thumb-link">--%>
                                        <%--<img src="../<c:out value="${book.getCoverUrl()}"/>" width="400px" height="400px" alt="">--%>
                                    <%--</a>--%>
                                <%--</div>--%>

                                <%--<div class="entry__text">--%>
                                    <%--<div class="entry__header">--%>
                                        <%--<h1 class="entry__title"><a href="single-standard.html"><c:out value="${book.getName()}"/></a></h1>--%>
                                        <%--<div class="entry__date">--%>
                                            <%--<a href="single-standard.html"><c:out value="${book.getPublishYear()}"/></a>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                    <%--<div class="entry__meta">--%>
                                        <%--<form method="post" action="/book">--%>
                                            <%--<input type="hidden" name="command" value="open_book_by_id">--%>
                                            <%--<input type="hidden" name="book_id" value="<c:out value="${book.getId()}"/>">--%>
                                            <%--<button type="submit">More</button>--%>
                                        <%--</form>--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                            <%--</article> <!-- end article -->--%>
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
                        <c:if test="${requestScope.currentPage!=1}">
                            <li><a class="pgn__num" href="/news?command=open_catalog&currentPage=1">First: 1</a></li>
                        </c:if>
                        <c:if test="${requestScope.currentPage>1}">
                            <li><a class="pgn__prev" href="/news?command=open_catalog&currentPage=${requestScope.currentPage-1}">Prev</a></li>
                        </c:if>
                        <%--<c:if test="${requestScope.currentPage>1}">--%>
                        <li><a class="pgn__num current"><c:out value="${requestScope.currentPage}"/></a></li>
                        <%--</c:if>--%>
                        <c:if test="${requestScope.currentPage<requestScope.totalPages}">
                            <li><a class="pgn__next" href="/news?command=open_catalog&currentPage=${requestScope.currentPage+1}">Next</a></li>
                        </c:if>
                        <c:if test="${requestScope.currentPage<requestScope.totalPages}">
                            <li><a class="pgn__num" href="/news?command=open_catalog&currentPage=${requestScope.totalPages}">Last: <c:out value="${requestScope.totalPages}"/></a></li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="row">
            <div class="col-full">
                <nav class="pgn">
                    <ul>
                        <c:forEach var="i" begin="5" end="30" step="5">
                            <c:choose>
                                <c:when test="${i == sessionScope.countBooks}">
                                    <li><a class="pgn__num current" href="/news?command=open_catalog&countBooks=${i}">${i}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a class="pgn__num" href="/news?command=open_catalog&countBooks=${i}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>


                        </c:forEach>
                    </ul>
                </nav>
            </div>
        </div>
    </section> <!-- s-content -->

    </body>
</html>
