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

        <div class="row narrow">
            <div class="col-full s-content__header">
                <h1>${title}</h1>

                <p class="lead">${headerP}</p>
            </div>
        </div>

        <div class="row narrow">
            <form method="post" action="/book">
                <input type="hidden" name="command" value="find_book">
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
                            <div class="col-lg-3" style="padding: 5px">
                                <div style="border: grey solid 2px; height: 600px;">
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
                                        <form action="/book" method="post">
                                            <input type="hidden" name="command" value="open_book">
                                            <input type="hidden" name="book_id" value="<c:out value="${book.id}"/>">
                                            <input type="submit" value="More" />
                                        </form>
                                    </div>
                                </div>
                            </div>

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
                        <c:if test="${requestScope.numberOfPage!=1}">
                            <li><a class="pgn__num" href="/news?command=open_book_catalog&numberOfPage=1">First: 1</a></li>
                        </c:if>
                        <c:if test="${requestScope.numberOfPage>1}">
                            <li><a class="pgn__prev" href="/news?command=open_book_catalog&numberOfPage=${requestScope.numberOfPage-1}">Prev</a></li>
                        </c:if>
                        <%--<c:if test="${requestScope.numberOfPage>1}">--%>
                        <li><a class="pgn__num current"><c:out value="${requestScope.numberOfPage}"/></a></li>
                        <%--</c:if>--%>
                        <c:if test="${requestScope.numberOfPage<requestScope.totalPages}">
                            <li><a class="pgn__next" href="/news?command=open_book_catalog&numberOfPage=${requestScope.numberOfPage+1}">Next</a></li>
                        </c:if>
                        <c:if test="${requestScope.numberOfPage<requestScope.totalPages}">
                            <li><a class="pgn__num" href="/news?command=open_book_catalog&numberOfPage=${requestScope.totalPages}">Last: <c:out value="${requestScope.totalPages}"/></a></li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="row">
            <div class="col-full">
                <nav class="pgn">
                    <ul>
                        <c:forEach var="i" begin="8" end="32" step="4">
                            <c:choose>
                                <c:when test="${i == sessionScope.countBooks}">
                                    <li><a class="pgn__num current" href="/news?command=open_book_catalog&countBooks=${i}">${i}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a class="pgn__num" href="/news?command=open_book_catalog&countBooks=${i}">${i}</a></li>
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
